package com.example.petsapce_week1.reviewrelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R

class ReviewAdapter(val itemList: ArrayList<ReviewItem>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_list,parent,false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.tv_name.text = itemList[position].nickName
        holder.tv_content.text = itemList[position].content
        holder.tv_time.text = itemList[position].time
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
    }
}