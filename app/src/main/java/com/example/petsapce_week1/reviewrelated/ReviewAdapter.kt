package com.example.petsapce_week1.reviewrelated

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ReviewItemListBinding
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

    override fun getItemViewType(position: Int): Int {
        return when (reviews[position]) {
            null -> TYPE_LOADING
            else -> TYPE_POST
        }
    }


    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: ReviewGetData.Review?) {
            val tv_time = itemView.findViewById<TextView>(R.id.tv_review_date)
            val tv_name = itemView.findViewById<TextView>(R.id.tv_user_nickname)
            val tv_content = itemView.findViewById<TextView>(R.id.readmoreTV_review_content)
        }
    }

/*    inner class ReviewViewHolder(val binding: View):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: Review) {
                    Log.d("ReviewGET data", data.toString())

                    binding.apply {
                        tvReviewDate.text = data.dayAfterCreated
                        readmoreTVReviewContent.text = data.content
                    }
                }
    }*/

    class LoadingViewHolder(inflatedView: View, itemView: View) : RecyclerView.ViewHolder(itemView) {
}
}
