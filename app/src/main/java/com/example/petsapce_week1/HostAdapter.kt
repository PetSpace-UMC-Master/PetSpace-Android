package com.example.petsapce_week1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HostAdapter(val itemList: ArrayList<HostItem>) : RecyclerView.Adapter<HostAdapter.HostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_list,parent,false)
        return HostViewHolder(view)
    }

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        holder.tv_loc.text = itemList[position].loc
        holder.tv_date.text = itemList[position].date
        holder.tv_price.text = itemList[position].price
    }


    class HostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_loc = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_date = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_price = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}