package com.example.petsapce_week1.placetogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.ActivitySeoulAccommoBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.FavoriteData
import retrofit2.Retrofit

class PlaceToGoRegionActivity : AppCompatActivity() {

    lateinit var binding : ActivitySeoulAccommoBinding

    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    //서비스 객체 생성
    var api: AccomoService = retrofit.create(AccomoService::class.java)

    var accommoList: MutableList<FavoriteBackendResponse.Favorite> = mutableListOf()

    lateinit var roomId: String
    var isLast : Boolean = false
    var accessToken : String ?= null
    var region : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeoulAccommoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val intent = Intent(this@SeoulAccommoActivity,PlaceGridAdapter::class.java)
        accommoList = intent.getSerializableExtra("accommoList") as MutableList<FavoriteBackendResponse.Favorite>
        isLast = intent.getBooleanExtra("isLast", false)
        accessToken = intent.getStringExtra("accessToken")
        region = intent.getStringExtra("region")
        Log.d("함께 서울", "$accommoList")
        Log.d("함께 서울 isLast", "$isLast")
        Log.d("함께 서울 at", "$accessToken")
        Log.d("함께 서울 region", "$region")
        var regionKor : String ?= null
        when(region){
            "SEOUL" -> regionKor = "서울"
            "BUSAN" -> regionKor = "부산"
            "CHUNGCHENOGDO" -> regionKor = "충청도"
            "JEJUDO" -> regionKor = "제주도"
            "DAEGU" -> regionKor = "대구"
            "GYEONGGIDO" -> regionKor = "경기도"
            "GWANGJU" -> regionKor = "광주"
            "JEOLLADO" -> regionKor = "전라도"
        }

        binding.tvRegion.text = regionKor

        //initRecyclerView()
        var adapter: PlaceToGoRegionAdapter = PlaceToGoRegionAdapter(accommoList)
        binding.recyclerviewMainHome.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMainHome.adapter = adapter
        binding.recyclerviewMainHome.isNestedScrollingEnabled = true
        Log.d("함께 어답터 실행됨", "ㅐㅐ")

        accessToken?.let {
            if (region != null) {
                api.getFavorites(it, region!!, 0, 2)
                    .enqueue(object : retrofit2.Callback<FavoriteBackendResponse> {
                        override fun onResponse(
                            call: retrofit2.Call<FavoriteBackendResponse>,
                            response: retrofit2.Response<FavoriteBackendResponse>
                        ) {
                            Log.d("함께 갈 곳 page+ 성공", response.toString())
                            Log.d("함께 갈 곳 page+", response.body().toString())
                            if (response.body()?.result != null) {
                                accommoList = response.body()?.result?.favorites?.toMutableList()!!
                                if (response.body()!!.result.isLast) {
                                    isLast = true
                                }
                                adapter = PlaceToGoRegionAdapter(accommoList)
                                binding.recyclerviewMainHome.layoutManager =
                                    LinearLayoutManager(this@PlaceToGoRegionActivity)
                                binding.recyclerviewMainHome.adapter = adapter
                                binding.recyclerviewMainHome.isNestedScrollingEnabled = true

                            } else {
                                Log.d("함께 갈 곳 page+", "empty")
                            }
                            if (response.body()?.result?.favorites?.size == 0) {
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.thisLayout, NoPlaceToGoFragment())
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }
                        override fun onFailure(
                            call: retrofit2.Call<FavoriteBackendResponse>,
                            t: Throwable
                        ) {
                            Log.d("함께 갈 곳 page+ failed", t.toString())
                        }
                    })
            }
        }
//        var page = 1
//        while(isLast){
//            accessToken?.let {
//                if (region != null) {
//                    api.getFavorites(it, region!!, page, 2)
//                        .enqueue(object : retrofit2.Callback<FavoriteBackendResponse> {
//                            override fun onResponse(
//                                call: retrofit2.Call<FavoriteBackendResponse>,
//                                response: retrofit2.Response<FavoriteBackendResponse>
//                            ) {
//                                Log.d("함께 갈 곳 page+ 성공", response.toString())
//                                Log.d("함께 갈 곳 page+", response.body().toString())
//                                if (response.body()?.result != null) {
//                                    accommoList = response.body()?.result?.favorites?.toMutableList()!!
//                                    if(response.body()!!.result.isLast){
//                                        isLast = true
//                                    }
//                                    adapter = PlaceToGoRegionAdapter(accommoList)
//                                    binding.recyclerviewMain.layoutManager = LinearLayoutManager(this@PlaceToGoRegionActivity)
//                                    binding.recyclerviewMain.adapter = adapter
//                                    binding.recyclerviewMain.isNestedScrollingEnabled = true
//
//                                } else {
//                                    Log.d("함께 갈 곳 page+", "empty")
//                                }
//                            }
//
//                            override fun onFailure(
//                                call: retrofit2.Call<FavoriteBackendResponse>,
//                                t: Throwable
//                            ) {
//                                Log.d("함께 갈 곳 page+ failed", t.toString())
//                            }
//                        })
//                }
//            }
//            page+=1
//        }
        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    //var dataList = accommoList

//    private fun initRecyclerView() {
//
//        //기존 adapter(recyclerview adpater)
//        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
//            this, LinearLayoutManager.VERTICAL, false
//        )
//
//        binding.recyclerviewMain.adapter = adapter
//        binding.recyclerviewMain.isNestedScrollingEnabled = true
//
//        adapter.itemClickListener = object : PlaceToGoRegionAdapter.OnItemClickListener {
//            override fun OnItemClick(data: FavoriteData) {
//                val intent2 = Intent(this@PlaceToGoRegionActivity, AccMainActivity::class.java)
////                intent2.putExtra("image", data.img)
//                intent2.putExtra("roomId", roomId)
//                //intent2.putExtra("score", score)
//                Log.d("score2", data.price.toString())
//                intent2.putExtra("location", data.roomAddress)
//                intent2.putExtra("price", data.price)
////                intent2.putExtra("data", data)
//                startActivity(intent2)
//
//                Log.d("test", "test")
//            }
//
//        }
//
//    }

}
