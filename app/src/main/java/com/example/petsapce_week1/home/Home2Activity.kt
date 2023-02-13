package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.databinding.ActivityHome2Binding
import com.example.petsapce_week1.home.homefragment.SortViewModel
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.Home2Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Home2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding
    val btn1House = "HOUSE"
    val btn2Campsite = "CAMPSITE"
    val btn3Downtown = "DOWNTOWN"
    val btn4Country = "COUNTRY"
    val btn5Beach = "BEACH"

    val sortDefault = "ID_DESC"
    val sortPriceDesc = "PRICE_ASC"
    val sortPriceAsc = "PRICE_DESC"
    val sortReviewCount = "REVIEW_COUNT_DESC"
    val sortReviewScore = "AVERAGE_REVIEW_SCORE_DESC"

    //스피너 및 버튼 전역변수
    var spinnerCheck:String = ""
    var buttonCheck:String = ""

    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    lateinit var viewModel: SortViewModel

    //child apdater

    var dataList = ArrayList<Home2MainData>()
    lateinit var spinner: Spinner
    lateinit var roomId: String

    lateinit var adapter: Home2MainAdapter

    //초기 검색값 세팅
    lateinit var searchText:String
    lateinit var startDay:String
    lateinit var endDay:String
    var adult = 0
    var child = 0
    var animal = 0
    var people = adult+child


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)

        //검색결과 : 최근등록순으로 정렬
        initFirst()
        //스피너 초기화
        initSpinner()
        //버튼 초기화
        initButtonSort()
        initRecyclerView()
        //이전으로
        initBefore()

    }



    private fun initFirst() {
         searchText = intent.getStringExtra("searchText").toString().trim()
        /*if (searchText == " "){
            searchText = ""
        }*/
         startDay = intent.getStringExtra("startDay").toString()
         endDay = intent.getStringExtra("endDay").toString()
         adult = intent.getIntExtra("adult", -1)
         child = intent.getIntExtra("child", -1)
         people = adult + child
         animal = intent.getIntExtra("animal", -1)
        Log.d("tag", searchText.toString())
        Log.d("tag1", startDay.toString())
        Log.d("tag2", endDay.toString())
        Log.d("tag3", adult.toString())
        Log.d("tag4", child.toString())
        Log.d("tag5", animal.toString())

        binding.textChange.text = searchText
        updateTripple(0,spinnerCheck,buttonCheck,startDay,endDay,searchText,people,animal)


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
                        spinnerCheck = sortDefault
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                        Log.d("tag", "spinner : $spinnerCheck button : $buttonCheck")

                    }
                    "높은가격순" -> {
                        spinnerCheck = sortPriceAsc
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                    }
                    "낮은가격순" -> {
                        spinnerCheck = sortPriceDesc
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                    }
                    "평점높은순" -> {
                        spinnerCheck = sortReviewScore
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                    }
                    "리뷰많은순" -> {
                        spinnerCheck = sortReviewCount
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                    }
                    else -> {
                        spinnerCheck = sortDefault
                        updateTripple(0, spinnerCheck, buttonCheck,startDay,endDay, searchText,people,animal)
                    }
                }
            }
        }

    }


    private fun initButtonSort() {
        binding.apply {
            b1.setOnClickListener {
                buttonCheck = btn1House
                updateTripple(0, "", buttonCheck,startDay.toString(),endDay.toString(), searchText,people,animal)
            }
            b2.setOnClickListener {
                buttonCheck = btn2Campsite
                updateTripple(0, "", buttonCheck,startDay.toString(),endDay.toString(), searchText,people,animal)
            }
            b3.setOnClickListener {
                buttonCheck = btn3Downtown
                updateTripple(0, "", buttonCheck,startDay.toString(),endDay.toString(), searchText,people,animal)
            }
            b4.setOnClickListener {
                buttonCheck = btn4Country
                updateTripple(0, "", buttonCheck,startDay.toString(),endDay.toString(), searchText,people,animal)
            }
            b5.setOnClickListener {
                buttonCheck = btn5Beach
                updateTripple(0, "", buttonCheck,startDay.toString(),endDay.toString(), searchText,people,animal)
            }
        }
    }

    fun updateTripple(
        page: Int,
        sort: String,
        category: String,
        startDay: String,
        endDay: String,
        keyword: String,
        people: Int,
        pets: Int
    ) {

        api.getAll(page, sort, category, startDay, endDay,keyword,people,pets)
            .enqueue(object : Callback<Home2Response> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<Home2Response>,
                    response: Response<Home2Response>
                ) {
                  /*  binding.recyclerviewMain.visibility = View.VISIBLE
                    binding.sorrydog.visibility = View.GONE*/
                    val usersSort = response.body()

                    if (usersSort != null && usersSort.result != null) {
                        val resultSize = usersSort.result.size
//                    Log.d("tripple", usersSort.result.toString())
                        val dataList = ArrayList<Home2MainData>()
                        var statdate = ""
                        var endDate = ""

                        Log.d("PRICE_DESCgggg", usersSort.result.toString())


                        for (i in 0 until resultSize) {
                            roomId = usersSort.result[i].roomId.toString()
//                        val availDaysList = usersSort.result[i].availableDays.size
                            val availImageSize = usersSort.result[i].roomImages.size

                            var childataList = ArrayList<Home2ChildData>()
                            for (j in 0 until availImageSize) {
                                childataList.add(Home2ChildData(usersSort.result[i].roomImages[j]))

                            }

                            dataList.add(
                                Home2MainData(
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
                        binding.recyclerviewMain.visibility = View.GONE
                        binding.sorrydog.visibility = View.VISIBLE
                        Log.d("PRICE_DESCgggg", response.code().toString())

                    }
                }

                override fun onFailure(call: Call<Home2Response>, t: Throwable) {
                    Log.d("PRICE_DESC", t.message.toString())
                }
            })

    }


    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        adapter = Home2MainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false
    }

    private fun initBefore() {
        binding.imgBack.setOnClickListener {
            val intent = Intent(this, HomeResearchActivity::class.java)
            startActivity(intent)
        }

    }

}