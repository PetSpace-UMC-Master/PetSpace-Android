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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentHomeBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
//import kotlinx.android.synthetic.main.home_main_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment : Fragment() {

    val btn1House = "HOUSE"
    val btn2Campsite = "CAMPSITE"
    val btn3Downtown = "DOWNTOWN"
    val btn4Country = "COUNTRY"
    val btn5Beach = "BEACH"

    val sortPriceDesc = "PRICE_DESC"
    val sortPriceAsc = "PRICE_ASC"
    val sortReviewCount = "REVIEW_COUNT_DESC"
    val sortReviewScore = "AVERAGE_REVIEW_SCORE_DESC"


    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    lateinit var viewModel: SortViewModel

    //child apdater 이미지
    private lateinit var binding: FragmentHomeBinding

    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter
    lateinit var spinner: Spinner
    lateinit var roomId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)


        var dataList3 = ArrayList<HomeMainData>()

        dataList3 = viewModel.update()
        for (i in 0 until dataList3.size) {

            Log.d("tag", dataList3[i].toString())
        }

        Log.d("tag", dataList3.size.toString())



        //네트워크 통신


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
                updateCategory(btn1House)
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
                                updateTripple(3, sortPriceAsc, btn1House)
                            }
                            "높은가격순" -> {
                                updateTripple(3, sortPriceAsc, btn1House)
                            }
                            "낮은가격순" -> {
                                updateTripple(3, sortPriceDesc, btn1House)
                            }
                            "평점높은순" -> {
                                updateTripple(3, sortReviewScore, btn1House)
                            }
                            "리뷰많은순" -> {
                                updateTripple(3, sortReviewCount, btn1House)
                            }
                            else -> {
                                updateTripple(3, sortPriceAsc, btn1House)
                            }
                        }
                    }
                }
            }
            b2.setOnClickListener {
                updateCategory(btn2Campsite)
            }
            b3.setOnClickListener {
                updateCategory(btn3Downtown)
            }
            b4.setOnClickListener {
                updateCategory(btn4Country)

            }
            b5.setOnClickListener {
                updateCategory(btn5Beach)

            }

        }

    }

    fun updateTripple(page: Int, sort: String, category: String) {
        ArrayList<HomeMainData>()

        api.getTriple(page, sort, category).enqueue(object : Callback<HomeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("tripple", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    val dataList = ArrayList<HomeMainData>()
//                    var dateList = arrayListOf(1,2,3)
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<HomeChildData>()
                        for (j in 0 until availImageSize) {
                            childataList.add(HomeChildData(R.drawable.map))
//                            Log.d("childataList",usersSort.result[i].roomImages[j])

                        }

                        if (availDaysList != 0) {
                            statdate = usersSort.result[i].availableDays[0]
                            endDate = usersSort.result[i].availableDays[availDaysList - 1]
                        }


                        dataList.add(
                            HomeMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                "$statdate~$endDate",
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview
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


    @SuppressLint("NotifyDataSetChanged")
    fun updateCategory(category: String) {
        val call = api.getCategory(category)
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()

                if (usersSort != null) {
                    Log.d("PRICE_DESC", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    var dataList = ArrayList<HomeMainData>()
//                    var dateList = arrayListOf(1,2,3)
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<HomeChildData>()
                        for (j in 0 until 5) {
                            childataList.add(HomeChildData(R.drawable.map))
//                            Log.d("childataList",usersSort.result[i].roomImages[j])

                        }

                        if (availDaysList != 0) {
                            statdate = usersSort.result[i].availableDays[0]
                            endDate = usersSort.result[i].availableDays[availDaysList - 1]
                        }


                        val add = dataList.add(
                            HomeMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                "$statdate~$endDate",
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview
                            )

                        )
                    }

//                        childataList.add(HomeChildData(R.drawable.map))


                    adapter.items = dataList
                    adapter.notifyDataSetChanged()

                } else {
                    Log.d("PRICE_DESC오류", response.code().toString())
                }
                // handle users
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE 연결 실패", t.message.toString())
            }
        })

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
                        updateSort(sortPriceAsc)
                    }
                    "높은가격순" -> {
                        updateSort(sortPriceAsc)
                    }
                    "낮은가격순" -> {
                        updateSort(sortPriceDesc)
                    }
                    "평점높은순" -> {
                        updateSort(sortReviewScore)
                    }
                    "리뷰많은순" -> {
                        updateSort(sortReviewCount)
                    }
                    else -> {
                        updateSort(sortPriceAsc)
                    }
                }
            }
        }

    }

    fun updateSort(sort: String) {
        val call = api.getSort(sort)
        call.enqueue(object : Callback<HomeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()

                if (usersSort != null) {
                    Log.d("PRICE_DESC", usersSort.result.toString())
                    val resultSize = usersSort.result.size
                    val dataList = ArrayList<HomeMainData>()
//                    var dateList = arrayListOf(1,2,3)
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        //이미지 입력
                        val childataList = ArrayList<HomeChildData>()
                        for (j in 0 until 5) {
                            childataList.add(HomeChildData(R.drawable.map))
//                            childataList.add(HomeChildData(usersSort.result[i].roomImages[j])
//                            Log.d("childataList",usersSort.result[i].roomImages[j])
                        }

                        //가용날짜 체크
                        if (availDaysList != 0) {
                            statdate = usersSort.result[i].availableDays[0]
                            endDate = usersSort.result[i].availableDays[availDaysList - 1]
                        }


                        dataList.add(
                            HomeMainData(
                                childataList,
                                usersSort.result[i].averageReviewScore,
                                usersSort.result[i].city + ", " + usersSort.result[i].district,
                                "$statdate~$endDate",
                                usersSort.result[i].price,
                                usersSort.result[i].numberOfReview
                            )

                        )
                    }




                    adapter.items = dataList
                    adapter.notifyDataSetChanged()

                } else {
                    Log.d("PRICE_DESC오류", response.code().toString())
                }
                // handle users
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("PRICE 연결 실패", t.message.toString())
            }
        })

    }


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
                intent.putExtra("score", roomId)
                Log.d("score2", data.price.toString())
                intent.putExtra("location", data.location)
                intent.putExtra("date", data.date)
                intent.putExtra("price", data.price)
//                intent.putExtra("data", data)


                startActivity(intent)

                Log.d("test", "test")
            }


        }

    }


}