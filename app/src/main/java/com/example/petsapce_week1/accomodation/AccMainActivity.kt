package com.example.petsapce_week1.accomodation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccMainBinding.inflate(layoutInflater)

        // .bind와 .inflate 차이 / layoutinflater , view 객체 차이
        val includeView: View = binding.frameHost.root
        bindingHostBinding = ActivityAccHostBinding.bind(includeView)




        // 이런식으로 간략하게 쳐도됨
//        bindingHostBinding = ActivityAccHostBinding.bind(binding.frameHost.root)


        setContentView(binding.root)

        initRecyclerView()
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
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
        imgdataList.add(imageSlideData(R.drawable.home2))
    }

    private fun initRecyclerView() {

        binding.viewpager.adapter = accImgaeSlideAdapter(imgdataList)

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

}