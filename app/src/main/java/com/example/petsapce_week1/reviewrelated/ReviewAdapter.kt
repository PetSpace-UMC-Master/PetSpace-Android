package com.example.petsapce_week1.reviewrelated

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.home.homefragment.HomeMainData
import kotlinx.android.synthetic.main.review_item_list.view.*


class ReviewAdapter(val itemList: ArrayList<ReviewItem>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){

    interface OnItemClickListener {
        fun OnItemClick(data: HomeMainData)
    }

    var itemClickListener: OnItemClickListener? = null //초기값 null값


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_list,parent,false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.tv_name.text = itemList[position].nickName
        holder.tv_content.text = itemList[position].content
        holder.tv_time.text = itemList[position].time

        holder.itemView.img_review_1.setOnClickListener {
            Log.d("숙소 이미지 버튼 클릭", "ㅇ소")

            val intent = Intent(holder.itemView?.context, ReviewPhotosActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("숙소 이미지 버튼 클릭2", "ㅇ소")
        }
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