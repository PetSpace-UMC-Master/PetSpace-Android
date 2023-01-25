package com.example.petsapce_week1.reviewrelated

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.AccReviewRowBinding
import com.example.petsapce_week1.databinding.ReviewItemListBinding
import com.example.petsapce_week1.databinding.ReviewReadMorePhotolistBinding

class ReviewPhotosAdapter(
    private val slideItems : MutableList<Int>,
    private val viewPager2: ViewPager2
): RecyclerView.Adapter<ReviewPhotosAdapter.ViewPagerViewHolder>() {
    private val runnable = Runnable { slideItems.addAll(slideItems) }

    interface OnItemClickListener {
        fun OnItemClick(data: Int)
    }

    var itemClickListener: OnItemClickListener? = null

    /*
    inner class ViewHolder(val binding: ReviewItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imgReview1.setOnClickListener {
                itemClickListener?.OnItemClick(slideItems[adapterPosition])
                Log.d("슬라이드 인식", "220")
                //val intent = Intent(this@ViewHolder,ReviewPhotosActivity::class.java)
                //startActivity(intent)
            }
        }
    }
     */

    inner class ViewPagerViewHolder(val binding: ReviewReadMorePhotolistBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.reviewImg1
        fun onBind(image: Int) {
            imageView.setImageResource(image)
            imageView.setOnClickListener {
                itemClickListener?.OnItemClick(slideItems[adapterPosition])
                Log.d("슬라이드 인식", "220")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ReviewReadMorePhotolistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("슬라이드 인식", "111")
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewPhotosAdapter.ViewPagerViewHolder, position: Int) {
        holder.onBind(slideItems[position])
        if (position == slideItems.size - 1) {
            viewPager2.post(runnable)
            Log.d("슬라이드 인식", "222")
        }
    }

    override fun getItemCount(): Int =
        Int.MAX_VALUE
}
