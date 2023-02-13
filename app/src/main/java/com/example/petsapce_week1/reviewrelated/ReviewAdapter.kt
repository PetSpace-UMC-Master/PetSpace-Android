package com.example.petsapce_week1.reviewrelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.vo.Review

class ReviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_POST = 0
        private const val TYPE_LOADING = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (reviews[position]) {
            null -> TYPE_LOADING
            else -> TYPE_POST
        }
    }

    private val reviews = mutableListOf<Review?>()

    fun setReviews(reviews: List<Review>) {
        this.reviews.apply {
            clear()
            addAll(reviews)
        }
        notifyDataSetChanged()
    }

    fun addReviews(reviews: List<Review>) {
        this.reviews.addAll(reviews)
        notifyDataSetChanged()
    }

    fun setLoadingView(b: Boolean) {
        if (b) {
            android.os.Handler().post {
                this.reviews.add(null)
                notifyItemInserted(reviews.size - 1)
            }
        } else {
            if (this.reviews[reviews.size - 1] == null) {
                this.reviews.removeAt(reviews.size - 1)
                notifyItemRemoved(reviews.size)
            }
        }
    }

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_item_list, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.tv_name.text = itemList[position].nickName
        holder.tv_content.text = itemList[position].content
        holder.tv_time.text = itemList[position].dayAfterCreated
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_POST -> {
                val inflatedView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.review_item_list, parent, false)
                return ReviewViewHolder(inflatedView)
            }
            else -> {
                val inflatedView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.review_item_list, parent, false)
                return LoadingViewHolder(inflatedView, inflatedView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
/*        holder.tv_name.text = itemList[position].nickName
        holder.tv_content.text = itemList[position].content
        holder.tv_time.text = itemList[position].dayAfterCreated*/
        when (holder.itemViewType) {
            TYPE_POST -> {
                val reviewViewHolder = holder as ReviewViewHolder
                reviewViewHolder.bind(reviews[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return reviews.count()
    }
/*    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review?) {
        }

        val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
    }*/

/*    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review?) {

        }

        val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
        val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
    }*/

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review?) {
            val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
            val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
            val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
        }
    }




}
//class LoadingViewHolder(inflatedView: View?, itemView: View) : RecyclerView.ViewHolder(itemView) {
class LoadingViewHolder(inflatedView: View, itemView: View) : RecyclerView.ViewHolder(itemView) {

}
