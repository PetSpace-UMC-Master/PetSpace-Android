package com.example.petsapce_week1.reviewrelated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accomodation.scroll.reviewData
import com.example.petsapce_week1.databinding.AccReviewRowBinding

class ReviewPhotosAdapter(
    private val slideItems: MutableList<Int>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<ReviewPhotosAdapter.ViewPagerViewHolder>() {
    private val runnable = Runnable { slideItems.addAll(slideItems) }

    class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.review_img_1)
        fun onBind(image: Int) {
            imageView.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewPhotosAdapter.ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_review_read_more_photos, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewPhotosAdapter.ViewPagerViewHolder, position: Int) {
        holder.onBind(slideItems[position])
        if (position == slideItems.size - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int =
        Int.MAX_VALUE
}
