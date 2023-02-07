package com.example.petsapce_week1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHomeResearchBinding
import com.example.petsapce_week1.home.homefragment.HomeMainAdapter
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeResearchActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeResearchBinding
    lateinit var adapter:HomeResearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeResearchBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val mainVPAdapter = HomeResVPAdapter(this)
        val viewPager = binding.viewpager
//        initRecyclerView()

        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = mainVPAdapter
        dotsIndicator.attachTo(viewPager)




    }


/*
    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)

        val gridLayoutManager = GridLayoutManager(this,  3)
        gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerview.layoutManager = gridLayoutManager


        adapter = HomeResearchAdapter(dataList)
        binding.recyclerview.adapter = adapter


        */
/*    adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
                override fun OnItemClick(data: HomeMainData) {
                    val intent = Intent(context, Home2Activity::class.java)
                    intent.putExtra("price", data.price)
    //                intent.putExtra("data", data)
                    startActivity(intent)

                    Log.d("test", "test")
                }


            }*//*


    }
*/

}