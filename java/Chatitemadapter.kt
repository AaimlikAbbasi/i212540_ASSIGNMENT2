package com.example.assignment2


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.CartItem
import com.example.assignment2.R
class Chatitemadapter(private val context: Context, private val itemList: List<CartItem>) : RecyclerView.Adapter<Chatitemadapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name) // Assuming TextView with id item_name in your item layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false) // Replace R.layout.item_chat with your item layout resource
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemName.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}