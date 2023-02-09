package com.example.petsapce_week1.reviewrelated

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityReviewReadMorePhotosBinding

//
class ReviewPhotosActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewReadMorePhotosBinding
    private val slideImageHandler : Handler = Handler()
    private val slideImageRunnable = Runnable {
        binding.reviewViewpager.currentItem = binding.reviewViewpager.currentItem + 1
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewReadMorePhotosBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val imageList = arrayListOf<Int>().apply{
            for ( i in 0..24){
                add(R.drawable.pic_2)
            }
        }
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.reviewViewpager.apply {
            adapter = ReviewPhotosAdapter(imageList, binding.reviewViewpager)
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
        }

    }

    override fun onPause() {
        super.onPause()
        slideImageHandler.removeCallbacks(slideImageRunnable)
    }

}