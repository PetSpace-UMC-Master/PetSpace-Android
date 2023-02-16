package com.example.petsapce_week1.placetogo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivitySeoulAccommoBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import retrofit2.Retrofit

class PlaceToGoRegionActivity : AppCompatActivity() {

    lateinit var binding : ActivitySeoulAccommoBinding

    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    //서비스 객체 생성
    var api: AccomoService = retrofit.create(AccomoService::class.java)

    var accommoList: MutableList<FavoriteBackendResponse.Result.Favorite> = mutableListOf()

    lateinit var roomId: String
    var isLast : Boolean = false
    var accessToken : String ?= null
    var region : String ?= null
    var reviewCount :Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeoulAccommoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val intent = Intent(this@SeoulAccommoActivity,PlaceGridAdapter::class.java)
        accommoList = intent.getSerializableExtra("accommoList") as MutableList<FavoriteBackendResponse.Result.Favorite>
        isLast = intent.getBooleanExtra("isLast", false)
        accessToken = intent.getStringExtra("accessToken")
        region = intent.getStringExtra("region")
        reviewCount = intent.getIntExtra("reviewCount", 0)
        Log.d("함께 서울", "$accommoList")
        if(accommoList.isEmpty()){
            supportFragmentManager.beginTransaction()
                .replace(R.id.thisLayout, NoPlaceToGoFragment())
                //.addToBackStack(null)
                .commit()
        }
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
        var adapter: PlaceToGoRegionAdapter = PlaceToGoRegionAdapter(accommoList, accessToken!!)
        binding.recyclerviewMainHome.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMainHome.adapter = adapter
        binding.recyclerviewMainHome.isNestedScrollingEnabled = true
        binding.recyclerviewMainHome.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var isLoading = false
            var isLastPage = false
            var currentPage = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.recyclerviewMainHome.layoutManager as LinearLayoutManager
                //val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                Log.d("부산1", totalItemCount.toString())
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.d("부산2", lastVisibleItem.toString())
                if (!isLoading && !isLastPage && lastVisibleItem == totalItemCount - 1)  {
                    currentPage++
                    isLoading = true
                    region?.let {
                        api.getFavorites(accessToken!!, it, page = currentPage, size = 5).enqueue(object : retrofit2.Callback<FavoriteBackendResponse> {
                            override fun onResponse(
                                call: retrofit2.Call<FavoriteBackendResponse>,
                                response: retrofit2.Response<FavoriteBackendResponse>
                            ) {
                                Log.d("함께 갈 곳 스크롤", currentPage.toString())
                                val data = response.body()
                                Log.d("함께 갈 곳 스크롤", data.toString())
                                if (data?.result != null) {
                                    Log.d("함께 갈 곳 isLast", data.result.isLast.toString())
                                    if(data.result.isLast){
                                        isLastPage = true
                                    }
                                    else{
                                        adapter = PlaceToGoRegionAdapter(accommoList, accessToken!!)
                                        binding.recyclerviewMainHome.layoutManager =
                                            LinearLayoutManager(this@PlaceToGoRegionActivity)
                                        binding.recyclerviewMainHome.adapter = adapter
                                        binding.recyclerviewMainHome.isNestedScrollingEnabled = true
                                    }
                                }
                                else{
//                                    fragmentManager
//                                        .beginTransaction()
//                                        .add(R.id.thisLayout, NoPlaceToGoFragment() )
//                                        .addToBackStack(null)
//                                        .show(NoPlaceToGoFragment())
//                                        //.hide(PlaceToGoFragment())
//                                        .commit()
                                    val noplacetogofragment = NoPlaceToGoFragment()
                                    val placetogofragment = PlaceToGoFragment()
                                    Log.d("함께 갈 곳 2222", "222222")
                                    placetogofragment.parentFragmentManager
                                        .beginTransaction()
                                        .replace(R.id.thisLayout, noplacetogofragment)
                                        .addToBackStack(null)
                                        .commit()
                                }
                                isLoading = false

                            }

                            override fun onFailure(call: retrofit2.Call<FavoriteBackendResponse>, t: Throwable) {
                                Log.d("함께 갈 곳 스크롤ㄴㄴ", "ㄴㄴ")
                                isLoading = false

                            }
                        })

                    }
                }
            }
        })
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}


//
//        accessToken?.let {
//            if (region != null) {
//                api.getFavorites(it, region!!, 0, 3)
//                    .enqueue(object : retrofit2.Callback<FavoriteBackendResponse> {
//                        override fun onResponse(
//                            call: retrofit2.Call<FavoriteBackendResponse>,
//                            response: retrofit2.Response<FavoriteBackendResponse>
//                        ) {
//                            Log.d("함께 갈 곳 page+ 성공", response.toString())
//                            Log.d("함께 갈 곳 page+", response.body().toString())
//                            if (response.body()?.result != null) {
//                                accommoList = response.body()?.result?.favorites?.toMutableList()!!
//                                if (response.body()!!.result.isLast) {
//                                    isLast = true
//                                }
//                                adapter = PlaceToGoRegionAdapter(accommoList, accessToken!!)
//                                binding.recyclerviewMainHome.layoutManager =
//                                    LinearLayoutManager(this@PlaceToGoRegionActivity)
//                                binding.recyclerviewMainHome.adapter = adapter
//                                binding.recyclerviewMainHome.isNestedScrollingEnabled = true
//                                //binding.recyclerviewMainHome.btn_heart.
//                            } else {
//                                Log.d("함께 갈 곳 page+", "empty")
//                            }
//                            if (response.body()?.result?.favorites?.size == 0) {
//                                supportFragmentManager.beginTransaction()
//                                    .replace(R.id.thisLayout, NoPlaceToGoFragment())
//                                    .addToBackStack(null)
//                                    .commit()
//                            }
//                        }
//                        override fun onFailure(
//                            call: retrofit2.Call<FavoriteBackendResponse>,
//                            t: Throwable
//                        ) {
//                            Log.d("함께 갈 곳 page+ failed", t.toString())
//                        }
//                    })
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

