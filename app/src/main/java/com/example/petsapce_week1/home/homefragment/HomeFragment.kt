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
import com.example.petsapce_week1.databinding.FragmentHomeBinding
import com.example.petsapce_week1.home.HomeResearchActivity
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import kotlinx.android.synthetic.main.home_main_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment : Fragment(), View.OnClickListener {

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
    var page = 0
    var spinnerCheck:String = ""
    var buttonCheck:String = ""

    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    lateinit var viewModel: SortViewModel

    //child apdater
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



        //네트워크 통신


        //버튼정렬
        initButton()
        //스피너정렬
        initSpinner()
        //리사이클러뷰
        initRecyclerView()
        //다음페이지
        initNext()

//        initButtonSort()
//        initAddData()

        // Inflate the layout for this fragment
        return binding.root
    }

    //버튼 정렬
    override fun onClick(v: View?) {
        when (v?.id) {
            binding.b1.id -> {
                buttonCheck = btn1House
                updateTripple(page,spinnerCheck,buttonCheck)
            }
            binding.b2.id -> {
                buttonCheck = btn2Campsite
                updateTripple(page,spinnerCheck,buttonCheck)
            }
            binding.b3.id -> {
                buttonCheck = btn3Downtown
                updateTripple(page,spinnerCheck,buttonCheck)
            }
            binding.b4.id -> {
                buttonCheck = btn4Country
                updateTripple(page,spinnerCheck,buttonCheck)
            }
            binding.b5.id -> {
                buttonCheck = btn5Beach
                updateTripple(page,spinnerCheck,buttonCheck)
            }
        }
    }

    //스피너 정렬
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
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                    "높은가격순" -> {
                        spinnerCheck = sortPriceAsc
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                    "낮은가격순" -> {
                        spinnerCheck = sortPriceDesc
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                    "평점높은순" -> {
                        spinnerCheck = sortReviewScore
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                    "리뷰많은순" -> {
                        spinnerCheck = sortReviewCount
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                    else -> {
                        spinnerCheck = sortDefault
                        updateTripple(page,spinnerCheck,buttonCheck)
                    }
                }
            }
        }

    }


    //삼중 정렬
    fun updateTripple(page: Int, sort: String, category: String) {
        ArrayList<HomeMainData>()

        api.getTriple(page, sort, category).enqueue(object : Callback<HomeResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                val usersSort = response.body()
                if (usersSort != null && usersSort.result != null) {
                    val resultSize = usersSort.result.size
                    val dataList = ArrayList<HomeMainData>()
//                    var dateList = arrayListOf(1,2,3)
                    var statdate = ""
                    var endDate = ""


                    for (i in 0 until resultSize) {
                        roomId = usersSort.result[i].roomId.toString()
//                        val availDaysList = usersSort.result[i].availableDays.size
                        val availImageSize = usersSort.result[i].roomImages.size

                        var childataList = ArrayList<HomeChildData>()
                        for (j in 0 until availImageSize) {
//                            childataList.add(HomeChildData(R.drawable.map))
                            childataList.add(HomeChildData(usersSort.result[i].roomImages[j]))
//                            Log.d("childataList",usersSort.result[i].roomImages[j])

                        }

                 /*       if (availDaysList != 0) {
                            statdate = usersSort.result[i].availableDays[0]
                            endDate = usersSort.result[i].availableDays[availDaysList - 1]
                        }*/


                        dataList.add(
                            HomeMainData(
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

        adapter = HomeMainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = true


        /*    adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
                override fun OnItemClick(data: HomeMainData) {
                    val intent = Intent(context, Home2Activity::class.java)
                    intent.putExtra("price", data.price)
    //                intent.putExtra("data", data)
                    startActivity(intent)

                    Log.d("test", "test")
                }


            }*/

    }


    fun initButton() {
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
        binding.b3.setOnClickListener(this)
        binding.b4.setOnClickListener(this)
        binding.b5.setOnClickListener(this)

    }

    private fun initNext() {
        binding.btnEdittext.setOnClickListener {
            val intent = Intent(context,HomeResearchActivity::class.java)
            startActivity(intent)
        }
    }


}