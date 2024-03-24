package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainActivity15 : AppCompatActivity() {

    private lateinit var username: TextView
    private lateinit var Fuser: FirebaseUser
    private lateinit var reference: DatabaseReference
    private lateinit var btn_send: ImageView
    private lateinit var text_send: EditText

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var mChat: MutableList<Chat>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main15)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        val signupText = findViewById<ImageView>(R.id.icon3)
        val videocall = findViewById<ImageView>(R.id.imageView15)

        signupText.setOnClickListener {
            startActivity(Intent(this, MainActivity18::class.java))
        }

        videocall.setOnClickListener {
            startActivity(Intent(this, MainActivity21::class.java))
        }

        username = findViewById(R.id.username)
        btn_send = findViewById(R.id.imageView20)
        text_send = findViewById(R.id.textsend)

        // Retrieve mentor ID from intent
        val mentorId = intent.getStringExtra("mentorid")
        if (mentorId.isNullOrEmpty()) {
            // Handle the case where mentorId is null or empty
            Toast.makeText(this, "Mentor ID not found", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if mentorId is not available
            return
        }

        val userId = intent.getStringExtra("mentorid")!!
        Fuser = FirebaseAuth.getInstance().currentUser!!
        if (Fuser == null) {
            // Handle the case where user is not authenticated
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if user is not authenticated
            return
        }

        reference = FirebaseDatabase.getInstance().getReference("mentors").child(mentorId)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                username.text = user?.name
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled if needed
            }
        })

        btn_send.setOnClickListener {
            val msg = text_send.text.toString()
            if (msg.isNotEmpty()) {
                sendMessage(Fuser.uid, userId, msg)
            } else {
                Toast.makeText(this@MainActivity15, "You can't send empty message", Toast.LENGTH_SHORT).show()
            }
            text_send.text.clear()
        }

        readMessage(userId)
    }

    private fun sendMessage(sender: String, receiver: String, message: String) {
        val reference = FirebaseDatabase.getInstance().reference

        val hashMap = HashMap<String, Any>()
        hashMap["sender"] = sender
        hashMap["receiver"] = receiver
        hashMap["message"] = message

        reference.child("Chats").push().setValue(hashMap)
    }

    private fun readMessage(userId: String) {
        mChat = mutableListOf()
        reference = FirebaseDatabase.getInstance().getReference("Chats")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mChat.clear()
                for (snapshot in dataSnapshot.children) {
                    val chat = snapshot.getValue(Chat::class.java)
                    if ((chat?.receiver == Fuser.uid && chat.sender == userId) || (chat?.receiver == userId && chat.sender == Fuser.uid)) {
                        chat?.let { mChat.add(it) }
                    }
                }
                messageAdapter = MessageAdapter(mChat, this@MainActivity15, userId)
                recyclerView.adapter = messageAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }
}