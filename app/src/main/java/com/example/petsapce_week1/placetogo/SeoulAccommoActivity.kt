package com.example.petsapce_week1.placetogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.ActivitySeoulAccommoBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.FavoriteData
import retrofit2.Retrofit

class SeoulAccommoActivity : AppCompatActivity() {

    lateinit var binding : ActivitySeoulAccommoBinding
    lateinit var accommoList : MutableList<FavoriteBackendResponse.Favorite>
    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()

    //서비스 객체 생성
    var api: AccomoService = retrofit.create(AccomoService::class.java)

    lateinit var adapter: PlaceToGoRegionAdapter
    lateinit var roomId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeoulAccommoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val intent = Intent(this@SeoulAccommoActivity,PlaceGridAdapter::class.java)
        accommoList = intent.getSerializableExtra("accommoList") as MutableList<FavoriteBackendResponse.Favorite>

        Log.d("함께 서울", "$accommoList")
        //initRecyclerView()

        adapter = PlaceToGoRegionAdapter(accommoList)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = true
        Log.d("함께 어답터 실행됨", "ㅐㅐ")

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, PlaceToGoFragment::class.java)
            startActivity(intent)
        }
    }

    //var dataList = accommoList

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = true

//        adapter.itemClickListener = object : PlaceToGoRegionAdapter.OnItemClickListener {
//            override fun OnItemClick(data: FavoriteData) {
//                val intent2 = Intent(this@SeoulAccommoActivity, Home2Activity::class.java)
////                intent2.putExtra("image", data.img)
//                intent2.putExtra("score", roomId)
//                Log.d("score2", data.price.toString())
//                intent2.putExtra("location", data.roomAddress)
//                intent2.putExtra("price", data.price)
////                intent2.putExtra("data", data)
//
//
//                startActivity(intent2)
//
//                Log.d("test", "test")
//            }
//
//        }

    }

}
