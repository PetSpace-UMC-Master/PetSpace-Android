package com.example.petsapce_week1.accomodation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accomodation.scroll.googleFragment
import com.example.petsapce_week1.accomodation.scroll.reviewAdapter
import com.example.petsapce_week1.accomodation.scroll.reviewData
import com.example.petsapce_week1.accomodation.scroll.reviewFragment
import com.example.petsapce_week1.databinding.ActivityAccHostBinding
import com.example.petsapce_week1.databinding.ActivityAccMainBinding

class AccMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityAccMainBinding
    lateinit var bindingHostBinding: ActivityAccHostBinding
    lateinit var adapter: accImgaeSlideAdapter
    var imgdataList = ArrayList<imageSlideData>()

    private val MIN_SCALE = 0.85f // 뷰가 몇퍼센트로 줄어들 것인지
    private val MIN_ALPHA = 0.5f // 어두워지는 정도를 나타낸 듯 하다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccMainBinding.inflate(layoutInflater)

        // .bind와 .inflate 차이 / layoutinflater , view 객체 차이
        val includeView: View = binding.frameHost.root
        bindingHostBinding = ActivityAccHostBinding.bind(includeView)

        // 이런식으로 간략하게 쳐도됨
        // bindingHostBinding = ActivityAccHostBinding.bind(binding.frameHost.root)

        setContentView(binding.root)

        binding.scrollView.isNestedScrollingEnabled = false

        initViewPager()
        initData()

        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameGoogle.id, googleFragment())
            .commitAllowingStateLoss()

        supportFragmentManager
            .beginTransaction()
            .add(binding.frameReview.id, reviewFragment())
            .commitAllowingStateLoss()
    }

    private fun initData() {
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.map))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.map))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.map))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.map))
    }

    private fun initViewPager() {

        binding.viewpager.adapter = accImgaeSlideAdapter(imgdataList)


        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimen 파일 안에 크기를 정의해두었다.
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // dimen 파일이 없으면 생성해야함
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewpager.apply {
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
            setPageTransformer(ZoomOutPageTransformer())
            offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘것인지
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        //기존 adapter(recyclerview adpater)
       /* binding.recyclerviewSlide.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
        adapter = accImgaeSlideAdapter(imgdataList)
        binding.recyclerviewSlide.adapter = adapter*/


       /* adapter.itemClickListener = object : accImgaeSlideAdapter.OnItemClickListener {
            override fun OnItemClick(data: imageSlideData) {
                *//*  Toast.makeText(getActivity(),"show", Toast.LENGTH_SHORT).show()
                   val intent = packageManager.getLaunchIntentForPackage(data.appackname)
                   startActivity(intent)*//*
                val intent = Intent(this@AccMainActivity, GifActivity::class.java)
                startActivity(intent)

                Log.d("test", "test")
            }


        }*/
    }

    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }


}