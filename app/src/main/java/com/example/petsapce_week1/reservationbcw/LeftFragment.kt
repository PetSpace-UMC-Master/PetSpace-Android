package com.example.petsapce_week1.reservationbcw

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.FragmentLeftBinding
import com.example.petsapce_week1.home.homefragment.HomeChildData
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.Home2Response
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LeftFragment : Fragment() {
    private lateinit var binding: FragmentLeftBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    var accessToken: String? = null
    var dataList = ArrayList<reserveMainData>()
    lateinit var adapter: reserveMainAdapter

    lateinit var roomId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        accessToken = "Bearer $accessToken"



        updateTripple2(2,"","")

        initRecyclerView()





        return binding.root
    }


    fun updateTripple2(page: Int, sort: String, category: String) {

        api.getTriple(page, sort, category).enqueue(object : Callback<HomeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val usersSort = response.body()
                if (usersSort != null && usersSort.result != null) {


                    val resultSize = usersSort.result.size
                    val dataList = ArrayList<reserveMainData>()
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
//                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<reserveChildData>()
                        for (j in 0 until availImageSize) {
                            childataList.add(reserveChildData(usersSort.result[i].roomImages[j]))
//                            Log.d("childataList",usersSort.result[i].roomImages[j])
                        }
                        /*       if (availDaysList != 0) {
                                   statdate = usersSort.result[i].availableDays[0]
                                   endDate = usersSort.result[i].availableDays[availDaysList - 1]
                               }*/

                        dataList.add(
                            reserveMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                "$statdate~$endDate",
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview,
                                usersSort.result[i].roomId
                            )
                        )
                    }

                    adapter.items = dataList
                    adapter.notifyDataSetChanged()

                } else {
                    Log.d("PRICE_DESC", response.code().toString())

                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE_DESC", t.message.toString())
            }
        })

    }



    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        adapter = reserveMainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false

    }


    /*  private fun getAccessToken() {
          val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
          accessToken = atpref.getString("accessToken", "default")
          accessToken = "Bearer $accessToken"
      }*/


}