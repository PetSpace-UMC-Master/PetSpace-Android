package com.example.petsapce_week1.reviewrelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityReviewPhotosBinding
import com.example.petsapce_week1.databinding.ReviewPhotosViewpagerListBinding


class ReviewAdapter(var itemList: ArrayList<ReviewItem>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    private lateinit var binding : ActivityReviewPhotosBinding
    var datas = mutableListOf<ReviewItem>()

    interface OnItemClickListener{
        fun onItemClick(v:View, data: ReviewItem, pos: Int)
    }
    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_list,parent,false)
        binding = ActivityReviewPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.tv_name.text = itemList[position].nickName
        holder.tv_content.text = itemList[position].content
        holder.tv_time.text = itemList[position].time


        //holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
        //val imgProfile : ImageView = itemView.findViewById(R.id.review_viewpager)
    }
}