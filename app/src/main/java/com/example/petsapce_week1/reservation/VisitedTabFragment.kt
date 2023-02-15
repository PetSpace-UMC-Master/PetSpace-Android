package com.example.petsapce_week1.reservation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentVisitedTabBinding
import com.example.petsapce_week1.databinding.VisitedAccommoListBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.placetogo.PlaceToGoRegionAdapter
import com.example.petsapce_week1.vo.ReservationReadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class VisitedTabFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var visitedAdapter: VisitedTabAdapter

    var accommoList = mutableListOf<ReservationReadResponse.Reservation>()

    lateinit var binding : FragmentVisitedTabBinding

    var accessToken : String ?= null

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : ReservationAPI = retrofit.create(ReservationAPI::class.java)

    var roomId : Long = 1

    override fun onResume() {
        super.onResume()
        getAccessToken()
    }
    private fun getAccessToken() {
        val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        Log.d("예약 방문 완료 액토","$accessToken")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("예약 왜 안될까?","4")

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVisitedTabBinding.inflate(layoutInflater)
        getAccessToken()

        Log.d("예약 왜 안될까?","3 fragment")

        recyclerView = binding.recyclerviewVisitedTab
//        recyclerView = view.findViewById(R.id.recyclerview_visited_tab)
        visitedAdapter = accessToken?.let { VisitedTabAdapter(it, accommoList) }!!
        recyclerView.adapter = visitedAdapter

//        binding = FragmentVisitedTabBinding.inflate(layoutInflater)
//        binding.recyclerviewVisitedTab.adapter = adapter
        binding.recyclerviewVisitedTab.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewVisitedTab.isNestedScrollingEnabled = true
        Log.d("예약 왜 안될까?","3 fragment")
        return binding.root
    }

}