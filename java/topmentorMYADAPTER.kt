package com.example.assignment2
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class topmentorMYADAPTER (list: ArrayList<topmentorMODEL>, c: Context):
    RecyclerView.Adapter<topmentorMYADAPTER.MyViewHolder>() {
    var list = list
    var context = c
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater
            .from(context)
            .inflate(R.layout.top_mentors, parent, false)
        return MyViewHolder(v)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.firsttext2.text = list[position].sessionCost // Set sessionCost to firsttext2 TextView
        holder.firsttext.text = list[position].name // Set name to firsttext TextView
        holder.smallCircleText.text = list[position].availability // Set availability to smallCircleText TextView
        holder.smallCircleText1.text = list[position].sessionCost
    }

    override fun getItemCount(): Int {

        return list.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firsttext: TextView = itemView.findViewById(R.id.firsttext)
        val firsttext2: TextView = itemView.findViewById(R.id.firsttext2)
        val smallCircleText: TextView = itemView.findViewById(R.id.smallCircleText)
        val smallCircleText1: TextView = itemView.findViewById(R.id.smallCircleText1)

        fun bind(mentor: topmentorMODEL) {
            firsttext.text = mentor.name
            firsttext2.text = mentor.sessionCost
            smallCircleText.text = mentor.availability
            smallCircleText1.text = mentor.sessionCost
        }
    }

}