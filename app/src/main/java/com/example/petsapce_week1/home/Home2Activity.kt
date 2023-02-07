package com.example.petsapce_week1.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.ActivityHome2Binding

class Home2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding
    var dataList = ArrayList<Home2Data>()
    lateinit var adapter: Home2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val intent2 = intent
        val name = intent2.getStringExtra("score")
        Log.d("score",name.toString())

    }


    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        adapter = Home2Adapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false



//        dataList.add(Home2Data(data.img,data.score,data.location,data.price))


    }

}