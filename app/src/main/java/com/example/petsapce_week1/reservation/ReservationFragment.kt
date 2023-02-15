package com.example.petsapce_week1.reservation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReservationBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper

import com.example.petsapce_week1.vo.ReservationReadResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReservationFragment : Fragment() {

    private lateinit var mContext: Context
    lateinit var binding : FragmentReservationBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : ReservationAPI = retrofit.create(ReservationAPI::class.java)

    var accessToken : String ?= null
    var accommoList = ArrayList<ReservationReadResponse.Reservation>()

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("예약 화면1", "onViewCreated")

        val tabLayout = binding.reservTabLayout
        val viewPager = binding.reservViewpager
        Log.d("예약 화면2", "${accessToken}")

        getAccessToken()

        if (accessToken != null) {
            viewPager.adapter = ReservationAdapter(accessToken!!, requireActivity())
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
                val fragment : Fragment
                if(position == 0){
                    fragment = ReservationTabFragment()
                }
                else{
                    fragment = VisitedTabFragment()
                }
                Log.d("예약 탭", position.toString())
                Log.d("예약 탭", fragment.toString())
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.menu_frame_reserv, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        //initViewPager()

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
                        accommoList = (response.body()?.result?.reservations?.toMutableList() as ArrayList<ReservationReadResponse.Reservation>?)!!
                        Log.d("예약 read 1 리스트", accommoList.toString())
                        val bundle = Bundle()
                        //bundle.putParcelable("accommoList", accommoList)
                        bundle.putSerializable("accommoList", accommoList)

                        //bundle.putParcelableArrayList("accommoList", ArrayList(accommoList)) // add data to the bundle
                        val fragment = ReservationTabFragment()
                        fragment.arguments = bundle

                        //val bundle = Bundle()
                        //bundle.putParcelableArrayList("key", ArrayList(myDataList)) // add mutableList of data class to the bundle
                        //val fragment = MyFragment()
                        //fragment.arguments = bundle // set the bundle as arguments of the fragment
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
                        accommoList = (response.body()?.result?.reservations?.toMutableList() as ArrayList<ReservationReadResponse.Reservation>?)!!
                        Log.d("예약 read 1", accommoList.toString())
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


    private fun initViewPager(){
        Log.d("언제 실행되나3", "지금")
        val reservationDone = ReservationTabFragment()
        val visitingDone = ReservationTabFragment()

        val adapter = accessToken?.let { activity?.let { it1 -> ReservationAdapter(it, it1) } }

//        adapter.addItems(reservationDone)
//        adapter.addItems(visitingDone)
//
//        binding.viewpager.adapter = adapter
//        binding.tabLayout.setupWithViewPager(binding.viewpager)
//
//        binding.tabLayout.getTabAt(0)?.customView = createView("예약 완료")
//        binding.tabLayout.getTabAt(1)?.customView = createView("방문 완료")

    }
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