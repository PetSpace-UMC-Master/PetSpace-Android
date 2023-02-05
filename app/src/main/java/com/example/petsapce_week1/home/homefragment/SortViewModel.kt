package com.example.petsapce_week1.home.homefragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.petsapce_week1.R
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SortViewModel:ViewModel() {
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()
    var api: homeAPI = retrofit.create(homeAPI::class.java)
    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter


    fun HouseRecent(){
        api.getDouble("PRICE_DESC","HOUSE").enqueue(object : Callback<HomeResponse> {
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    Log.d("Sort", body.result.toString())

                }
                else{
                    Log.d("PRICE_DESC", response.code().toString())

                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE_DESC", t.message.toString())
            }
        })

        var dataList = ArrayList<HomeMainData>()



/*
        for (i in 1..10) {
            var childataList = ArrayList<HomeChildData>()

            when (i) {

                1 -> {
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                    childataList.add(HomeChildData(R.drawable.map))
                }
                2 -> {
                    childataList.add(HomeChildData(R.drawable.home2))
                    childataList.add(HomeChildData(R.drawable.home2))
                    childataList.add(HomeChildData(R.drawable.home2))
                    childataList.add(HomeChildData(R.drawable.home2))

                }

            }
         */
/*   dataList.add(
                HomeMainData(
                    childataList,
                    5.00000,
                    "종로구, 서울",
                    11,
                    5000,136
                )
            )*//*



        }
*/

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }




}