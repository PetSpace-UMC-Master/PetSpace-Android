package com.example.petsapce_week1.reservation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReservationBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_reservation.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReservationFragment : Fragment() {

    private lateinit var mContext: Context
    lateinit var binding : FragmentReservationBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : ReservationAPI = retrofit.create(ReservationAPI::class.java)


    var accessToken : String ?= null

    private fun getAccessToken() {
        val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        accessToken = "Bearer $accessToken"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = context?.applicationContext!!
        binding = FragmentReservationBinding.inflate(layoutInflater)
        Log.d("예약 언제 실행되나1", "지금")
        getAccessToken()

        //initViewPager()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("예약 화면1", "onViewCreated")

        val tabLayout = binding.reservTabLayout
        val viewPager = binding.reservViewpager
        Log.d("예약 화면2", "${accessToken}")

        if(accessToken != null){
            viewPager.adapter = ReservationTabAdapter(accessToken!!, requireActivity())
            Log.d("예약 화면4", "onViewCreated")
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position) {
                0 -> tab.text = "예약 완료"
                1 -> tab.text = "방문 완료"
            }
        }.attach()
        Log.d("예약 화면3", "onViewCreated")

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                sendGetRequest(position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
    private fun sendGetRequest(position: Int) {
        if (position == 0) {
            accessToken?.let {
                api.getReservationReadUpcoming(it, page = 0, size = 5).enqueue(object :
                    Callback<ReservationReadResponse> {
                    override fun onResponse(
                        call: Call<ReservationReadResponse>,
                        response: Response<ReservationReadResponse>
                    ) {
                        Log.d("예약 read 1 ", response.toString())
                        Log.d("예약 read1", response.body().toString())
                    }

                    override fun onFailure(call: Call<ReservationReadResponse>, t: Throwable) {
                        Log.d("예약 통신 실패1", t.toString())
                    }

                })
            }
        }
        else if(position == 1){
            accessToken?.let {
                api.getReservationReadTerminate(it, page = 0, size = 5).enqueue(object :
                    Callback<ReservationReadResponse> {
                    override fun onResponse(
                        call: Call<ReservationReadResponse>,
                        response: Response<ReservationReadResponse>
                    ) {
                        Log.d("예약 read 1 ", response.toString())
                        Log.d("예약 read1", response.body().toString())
//                        val fragment = viewPager.adapter?.instantiateItem(viewPager, 1) as? MyFragment
//                        fragment?.updateUI(data)
                    }

                    override fun onFailure(call: Call<ReservationReadResponse>, t: Throwable) {
                        Log.d("예약 통신 실패1", t.toString())
                    }

                })
            }
    }
}


//    private fun initViewPager(){
//        Log.d("언제 실행되나3", "지금")
//        val reservationDone = ReservationTabFragment()
//        reservationDone.name = "예약 완료"
//        val visitingDone = ReservationTabFragment()
//        visitingDone.name = "방문 완료"
//
//        val adapter = ReservationTabAdapter(childFragmentManager)
//        adapter.addItems(reservationDone)
//        adapter.addItems(visitingDone)
//
//        binding.viewpager.adapter = adapter
//        binding.tabLayout.setupWithViewPager(binding.viewpager)
//
//        binding.tabLayout.getTabAt(0)?.customView = createView("예약 완료")
//        binding.tabLayout.getTabAt(1)?.customView = createView("방문 완료")
//
//    }
//    @SuppressLint("InflateParams")
//    private fun createView(tabName : String) : View {
//        //binding = FragmentReservationBinding.inflate(layoutInflater)
//        //mContext = context?.applicationContext!!
//        //initViewPager()
//        Log.d("언제 실행되나2", "지금")
//        val tabView2 = binding.viewpager.tabLayout
//        tabView2.tv_reservation_title.text = tabName
////        val tabView = LayoutInflater.from(mContext).inflate(R.layout.fragment_reservation_tab, null)
////        tabView.tv_reservation_title.text = tabName
////        return when(tabName){
////            "예약 완료" -> {
////                tabView
////            }
////            "방문 완료" -> {
////                tabView
////            }
////            else ->{
////                tabView
////            }
////        }
//        return tabView2
//    }


}