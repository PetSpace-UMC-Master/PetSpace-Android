package com.example.petsapce_week1.home.homefragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentHomeBinding
import com.example.petsapce_week1.databinding.FragmentReviewBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import kotlinx.android.synthetic.main.home_main_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment : Fragment() {
    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    //child apdater 이미지
    var childataList = ArrayList<HomeChildData>()
    private lateinit var binding: FragmentHomeBinding
    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter
    lateinit var spinner: Spinner
    lateinit var viewModel: SortViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        //네트워크 통신

        //낮은가격순
        val call = api.getCategory("HOUSE")
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()

                if (usersSort != null) {
                    Log.d("PRICE_DESC", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    var dataList2 = ArrayList<HomeMainData>()


                    for (i in 0..resultSize) {
                        val availDaysList = usersSort.result[i].availableDays.size
                        var childataList = ArrayList<HomeChildData>()

//                        childataList.add(HomeChildData(R.drawable.map))

                        dataList2.add(
                            HomeMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                usersSort.result[i].availableDays[0] + " ~ " + usersSort.result[i].availableDays[availDaysList],
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview
                            )

                        )
                    }

                    /*  response.takeIf { it.isSuccessful }
                          ?.body()
                          .let {
                              if (it != null) {
                                  for (item in it.result.get(it).siz) {

                                  }
                              }
                          }


                      usersSort.result.city*/
                } else {
                    Log.d("PRICE_DESC오류", response.code().toString())
                }
                // handle users
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE 연결 실패", t.message.toString())
            }
        })

        api.getSort("PRICE_DESC").enqueue(object : Callback<HomeResponse> {
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val body = response.body()
                if (body != null) {

                    Log.d("PRICE_DESC", body.result.toString())

                } else {
                    Log.d("PRICE_DESC", response.code().toString())

                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE_DESC", t.message.toString())
            }
        })



        initRecyclerView()
        initSpinner()
        initButtonSort()
//        initAddData()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initButtonSort() {
        binding.apply {
            b1.setOnClickListener {
                updateHouse()
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d(
                            "MainActivity",
                            "onItemSelected : $position, ${spinner.getItemAtPosition(position)}"
                        )
                        when (spinner.getItemAtPosition(position)) {
                            "최근등록순" -> {
                                updateHouseRcent()
                            }
                            "높은가격순" -> {
                                updatePriceHigh()
                            }
                            "낮은가격순" -> {
                                updatePriceRow()
                            }
                            "평점높은순" -> {
                                updateScoreHigh()
                            }
                            "리뷰많은순" -> {
                                updateReview()
                            }
                            else -> {
                                updateRecent()
                            }
                        }
                    }
                }
            }
            b2.setOnClickListener {
                updateCamp()
            }
            b3.setOnClickListener {
                updateDowntown()
            }
            b4.setOnClickListener {
                updateCountry()
            }
            b5.setOnClickListener {
                updateBeach()
            }

        }

    }

    private fun updateHouseRcent() {
        var dataList = ArrayList<HomeMainData>()



        for (i in 1..10) {
            var childataList = ArrayList<HomeChildData>()

         /*   when (i) {

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

            }*/
            /*   dataList.add(
                   HomeMainData(
                       childataList,
                       5.00000,
                       "종로구, 서울",
                       11,
                       5000, 136
                   )
               )*/

        }
    }


    private fun updateBeach() {
        var dataList = ArrayList<HomeMainData>()

        for (i in 1..10) {
            var childataList = ArrayList<HomeChildData>()



        }

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateCountry() {
        //낮은가격순
        val call = api.getCategory("HOUSE")
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()

                if (usersSort != null) {
                    Log.d("PRICE_DESC", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    var dataList2 = ArrayList<HomeMainData>()


                    for (i in 0..resultSize) {
                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<HomeChildData>()
                        for (j in 0..availImageSize ){
                            childataList.add(HomeChildData(usersSort.result[i].roomImages[j]))

                        }



//                        childataList.add(HomeChildData(R.drawable.map))

                        dataList2.add(
                            HomeMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                usersSort.result[i].availableDays[0] + " ~ " + usersSort.result[i].availableDays[availDaysList],
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview
                            )

                        )
                    }

                    /*  response.takeIf { it.isSuccessful }
                          ?.body()
                          .let {
                              if (it != null) {
                                  for (item in it.result.get(it).siz) {

                                  }
                              }
                          }


                      usersSort.result.city*/
                } else {
                    Log.d("PRICE_DESC오류", response.code().toString())
                }
                // handle users
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE 연결 실패", t.message.toString())
            }
        })
        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateDowntown() {
        var dataList = ArrayList<HomeMainData>()
        /*   dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
           dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))*/

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateCamp() {
        var dataList = ArrayList<HomeMainData>()

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateHouse() {
        var dataList = ArrayList<HomeMainData>()
       /* for (i in 1..10) {
            var childataList = ArrayList<HomeChildData>()

            when (i) {

                1 -> {
                    childataList.add(HomeChildData(R.drawable.map))


                }
                2 -> {
                    childataList.add(HomeChildData(R.drawable.home2))
                    childataList.add(HomeChildData(R.drawable.home2))

                }

            }
            *//*    dataList.add(
                    HomeMainData(
                        childataList,
                        5.00000,
                        "종로구, 서울",
                        11,
                        5000, 136
                    )
                )
    *//*

        }*/



        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun initSpinner() {
        spinner = binding.spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d(
                    "MainActivity",
                    "onItemSelected : $position, ${spinner.getItemAtPosition(position)}"
                )
                when (spinner.getItemAtPosition(position)) {
                    "최근등록순" -> {
                        updateRecent()
                    }
                    "높은가격순" -> {
                        updatePriceHigh()
                    }
                    "낮은가격순" -> {
                        updatePriceRow()
                    }
                    "평점높은순" -> {
                        updateScoreHigh()
                    }
                    "리뷰많은순" -> {
                        updateReview()
                    }
                    else -> {
                        updateRecent()
                    }
                }
            }
        }

    }

    private fun updateReview() {
        TODO("Not yet implemented")
    }

    private fun updateScoreHigh() {
        TODO("Not yet implemented")
    }

    private fun updatePriceRow() {
        TODO("Not yet implemented")
    }

    fun updatePriceHigh() {
        var dataList = ArrayList<HomeMainData>()

        //낮은가격순
        val call = api.getSort("PRICE_DESC")
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                /*    usersSort?.let {
    //                    setAdapter(it.result)

                    }
    */
                if (usersSort != null) {

//                    var city = usersSort.result.city
                    Log.d("PRICE_DESC", usersSort.result.toString())
                } else {
                    Log.d("PRICE_DESC오류", response.code().toString())
                }


                // handle users
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("sort연결 실패", t.message.toString())
            }
        })




        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    fun updateRecent() {
        var dataList = ArrayList<HomeMainData>()


        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    /* private fun initAddData() {
         dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
         dataList.add(HomeMainData(R.drawable.home2, 5, "서초구", 11, 5000))
         dataList.add(HomeMainData(R.drawable.home2, 5, "서초구", 11, 1))
         dataList.add(HomeMainData(R.drawable.home2, 5, "서초구", 11, 5000))
         dataList.add(HomeMainData(R.drawable.home2, 5, "서초구", 11, 5000))
     }*/

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        adapter = HomeMainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = true




        adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
            override fun OnItemClick(data: HomeMainData) {
                val intent = Intent(context, Home2Activity::class.java)
//                intent.putExtra("image", data.img)
                intent.putExtra("score", data.price)
                Log.d("score2", data.price.toString())
                intent.putExtra("location", data.location)
                intent.putExtra("date", data.date)
                intent.putExtra("price", data.price)
//                intent.putExtra("data", data)


                startActivity(intent)

                Log.d("test", "test")
            }


        }


        /*    adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
                override fun OnItemClick(data: HomeMainData) {
                    *//*  Toast.makeText(getActivity(),"show", Toast.LENGTH_SHORT).show()
                   val intent = packageManager.getLaunchIntentForPackage(data.appackname)
                   startActivity(intent)*//*
                val intent = Intent(context, Home2Activity::class.java)
                intent.putExtra("image",data.img)
                startActivity(intent)

                Log.d("test", "test")
            }
        }*/
    }


}