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
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.FragmentHomeBinding
import com.example.petsapce_week1.home.Home2Activity
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class HomeFragment : Fragment() {
    //레트로핏 객체 생성
    var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()

    //서비스 객체 생성
    var api: homeAPI = retrofit.create(homeAPI::class.java)

    private lateinit var binding: FragmentHomeBinding
    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter
    lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        //네트워크 통신


        //낮은가격순
        val call = api.getSort("PRICE_DESC")
        call.enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
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

        api.getSort("PRICE_ASC").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_ASC", usersSort.result.toString())
                } else {
                    Log.d("PRICE_ASC", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })

        api.getSort("PRICE_ASC").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_ASC", usersSort.result.toString())
                } else {
                    Log.d("PRICE_ASC", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })

        api.getCategory("HOUSE").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_HOUSE", usersSort.result.toString())
                } else {
                    Log.d("PRICE_HOUSE", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })

        api.getCategory("CAMPSITE").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_CAMPSITE", usersSort.result.toString())
                } else {
                    Log.d("PRICE_CAMPSITE", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })

        api.getPage("0").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_page", usersSort.result.toString())
                } else {
                    Log.d("PRICE_page", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })

        api.getPage("3").enqueue(object : Callback<HomeResponse> {
            //통신 성공
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val usersSort = response.body()
                if (usersSort != null) {
                    Log.d("PRICE_page3", usersSort.result.toString())
                } else {
                    Log.d("PRICE_page3", response.code().toString())
                }

            }

            //통신 실패
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("연결 실패", t.message.toString())
            }

        })




        initRecyclerView()
        initButtonSort()
        initSpinner()
        //        initAddData()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initButtonSort() {
        binding.apply {
            b1.setOnClickListener {
                Log.d("touch","touch")
                updateHouse()
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

    private fun updateBeach() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateCountry() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateDowntown() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateCamp() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, ", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))

        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateHouse() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 3, "서초구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종dff로구, 서울", 11, 5000))

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
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))


        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updateScoreHigh() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.home2, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))


        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    private fun updatePriceRow() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 10, "aaaaaa", 11, 5000))
        dataList.add(HomeMainData(R.drawable.map, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))


        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    fun updatePriceHigh() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.map, 10, "종tjchrn", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))


        adapter.items = dataList
        adapter.notifyDataSetChanged()
    }

    fun updateRecent() {
        var dataList = ArrayList<HomeMainData>()
        dataList.add(HomeMainData(R.drawable.home2, 5, "종로구, 서울", 11, 5000))

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
        binding.recyclerviewMain.isNestedScrollingEnabled = false



        adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
            override fun OnItemClick(data: HomeMainData) {
                //val intent = Intent(context, AccMainActivity::class.java)
                val intent = Intent(context, Home2Activity::class.java)
                intent.putExtra("image", data.img)
                intent.putExtra("score", data.score)
                intent.putExtra("location", data.location)
                intent.putExtra("date", data.date)
                intent.putExtra("price", data.price)
                intent.putExtra("data", data)



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