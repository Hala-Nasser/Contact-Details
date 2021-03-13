package com.example.contactdetails

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(var activity: Activity, var data: MutableList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.viewName)
        val tvNumber: TextView = itemView.findViewById(R.id.viewNumber)
        val tvAddress: TextView = itemView.findViewById(R.id.viewAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.contact_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = data[position].name
        holder.tvNumber.text = data[position].number
        holder.tvAddress.text = data[position].address
    }
}