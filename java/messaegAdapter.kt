package com.example.assignment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MessageAdapter(private val mChat: List<Chat>, private val mContext: Context, private val imageURL: String) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private val MSG_TYPE_LEFT = 0
    private val MSG_TYPE_RIGHT = 1
    private var fuser: FirebaseUser? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == MSG_TYPE_RIGHT) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.chatitem_right, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.chatitem_left, parent, false)
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = mChat[position]
        holder.show_message.text = chat.message

        if (imageURL == "default") {
            holder.imageURL.setImageResource(R.mipmap.ic_launcher)
        } else {
            Glide.with(mContext).load(imageURL).into(holder.imageURL)
        }

    }

    override fun getItemCount(): Int {
        return mChat.size
    }

    override fun getItemViewType(position: Int): Int {
        fuser = FirebaseAuth.getInstance().currentUser
        return if (mChat[position].sender == fuser?.uid) {
            MSG_TYPE_RIGHT
        } else {
            MSG_TYPE_LEFT
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var show_message: TextView = itemView.findViewById(R.id.show_message)
        var imageURL: ImageView = itemView.findViewById(R.id.profileImage1)
    }
}
