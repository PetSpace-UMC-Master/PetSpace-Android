package com.example.petsapce_week1.reviewrelated

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.R
import com.example.petsapce_week1.vo.ReviewGetData


class ReviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_POST = 0
        private const val TYPE_LOADING = 1
    }

    private val reviews = mutableListOf<ReviewGetData.Review?>()

    fun setReviews(reviews: List<ReviewGetData.Review>) {
        this.reviews.apply {
            clear()
            addAll(reviews)
        }
        notifyDataSetChanged()
    }

    fun addReviews(reviews: List<ReviewGetData.Review>) {
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
        Log.d("리뷰 목록 items", "$reviews")
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: ReviewGetData.Review?) {
            Log.d("리뷰 더보기 데이터", review.toString())
            val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
            val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
            val profimg = itemView.findViewById<ImageView>(R.id.user_profile_img)
            val tv_date = itemView.findViewById<TextView>(R.id.tv_review_date)
            // 리뷰 이미지
/*            val img1 = itemView.findViewById<TextView>(R.id.img_review_1)
            val img2 = itemView.findViewById<TextView>(R.id.img_review_2)
            val img3 = itemView.findViewById<TextView>(R.id.img_review_3)
            val img4 = itemView.findViewById<TextView>(R.id.img_review_4)*/

            if (review != null) {
                tv_name.text = review.nickName
                tv_content.text = review.content
                tv_date.text = review.dayAfterCreated
                //img1.text = review.reviewImage
                itemView.apply {
                    Glide.with(context)
                        .load(review.profileImage)
                        .circleCrop()
                        .into(profimg)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_POST -> {
                val reviewViewHolder = holder as ReviewViewHolder
                reviewViewHolder.bind(reviews[position])
/*                val review: ReviewGetData.Review?
                val profimg = ReviewGetData.Review
                val profimg = itemView.findViewById<ImageView>(R.id.user_profile_img)

                holder.apply {
                    Glide.with()
                        .load(review.profileImage)
                        .into(profimg)
                }*/
            }
        }
    }

    override fun getItemCount(): Int {
        return reviews.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when (reviews[position]) {
            null -> TYPE_LOADING
            else -> TYPE_POST
        }
    }

    class LoadingViewHolder(inflatedView: View, itemView: View) : RecyclerView.ViewHolder(itemView) {
}
}
