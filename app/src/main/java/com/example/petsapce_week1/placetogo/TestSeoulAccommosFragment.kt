package com.example.petsapce_week1.placetogo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.FragmentTestSeoulAccommosBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteData
import retrofit2.Retrofit


class TestSeoulAccommosFragment() : Fragment() {
    val intent = Intent(context, PlaceGridAdapter::class.java)
    val accommoList = intent.getSerializableExtra("data") as FavoriteData
// Do something with the data class

    lateinit var binding : FragmentTestSeoulAccommosBinding
    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()

    //서비스 객체 생성
    var api: AccomoService = retrofit.create(AccomoService::class.java)

    var dataList = accommoList
    lateinit var adapter: PlaceToGoRegionAdapter
    lateinit var spinner: Spinner
    lateinit var roomId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTestSeoulAccommosBinding.inflate(layoutInflater)
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )

        adapter = PlaceToGoRegionAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = true

        adapter.itemClickListener = object : PlaceToGoRegionAdapter.OnItemClickListener {
            override fun OnItemClick(data: FavoriteData) {
                val intent = Intent(context, Home2Activity::class.java)
//                intent.putExtra("image", data.img)
                intent.putExtra("score", roomId)
                Log.d("score2", data.price.toString())
                intent.putExtra("location", data.roomAddress)
                intent.putExtra("price", data.price)
//                intent.putExtra("data", data)


                startActivity(intent)

                Log.d("test", "test")
            }

        }

    }


}