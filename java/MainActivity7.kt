package com.example.assignment2

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class MainActivity7 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var mentorList :ArrayList<topmentorMODEL>
    private lateinit var mentorAdapter: topmentorMYADAPTER
    private lateinit var dbRef: DatabaseReference
    private lateinit var mentorRecyclerView: RecyclerView
    private val TAG = "MainActivity7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.assignment2.R.layout.activity_main7)


        val editTextText = findViewById<EditText>(com.example.assignment2.R.id.editTextText)
        val helloAliText = "Hello, Ali"
        val aliColor = Color.parseColor("#0B7C71") // Desired color

        // Create a SpannableString to apply different styles to different parts of the text
        val spannableString = SpannableString(helloAliText)

        // Set color for "Ali"
        spannableString.setSpan(
            ForegroundColorSpan(aliColor),
            7,
            helloAliText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Set the SpannableString to the EditText
        editTextText.setText(spannableString)


        val signupText = findViewById<ImageView>(com.example.assignment2.R.id.icon3)

        // Set an OnClickListener for the "Signup" TextView
        signupText.setOnClickListener {
            // Start MainActivity3
            startActivity(Intent(this, MainActivity8::class.java))

        }



          val profile=findViewById<ImageView>(R.id.icon2)
        profile.setOnClickListener {
            // Start MainActivity3
            startActivity(Intent(this, MainActivity23::class.java))

        }
        mentorRecyclerView = findViewById<RecyclerView>(R.id.topmentor)
        mentorRecyclerView.layoutManager = LinearLayoutManager(this)
        mentorList = ArrayList() // Initialize mentorList
        mentorAdapter = topmentorMYADAPTER(mentorList, this)
        mentorRecyclerView.adapter = mentorAdapter

// Sample mentor data
        getmentordata()




        val addmentor = findViewById<ImageView>(com.example.assignment2.R.id.imageView5)

        // Set an OnClickListener for the "Signup" TextView
        addmentor.setOnClickListener {
            // Start MainActivity3
            startActivity(Intent(this, MainActivity12::class.java))

        }

        val chat = findViewById<ImageView>(com.example.assignment2.R.id.icon4)

        // Set an OnClickListener for the "Signup" TextView
        chat.setOnClickListener {
            // Start MainActivity3
            startActivity(Intent(this, MainActivity14::class.java))

        }




    }

private fun getmentordata() {
      dbRef = FirebaseDatabase.getInstance().getReference("mentors")
    dbRef.addValueEventListener(object : ValueEventListener {
        @SuppressLint("NotifyDataSetChanged")
        override fun onDataChange(snapshot: DataSnapshot) {
            mentorList.clear()
            if(snapshot.exists()){
                for(mentorSnapshot in snapshot.children){
                    val mentor = mentorSnapshot.getValue(topmentorMODEL::class.java)
                    if (mentor != null) {
                        mentorList.add(mentor)
                    }
                }

                mentorAdapter.notifyDataSetChanged()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e(TAG, "Failed to read value.", error.toException())
        }

    })

}
}