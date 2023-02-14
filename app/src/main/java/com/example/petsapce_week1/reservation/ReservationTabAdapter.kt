package com.example.petsapce_week1.reservation

import android.content.ContentProvider
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.petsapce_week1.databinding.FacilitiesItemListBinding
import com.example.petsapce_week1.databinding.FragmentReservationBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import kakao.k.p
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReservationTabAdapter(val accessToken: String, fragmentActivity: FragmentActivity) :
    RecyclerView.Adapter<ReservationTabAdapter.ViewHolder>() {

    //var accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6eGN2YjA2MDlAbmF2ZXIuY29tIiwiaWF0IjoxNjc2MzgxNzUxLCJleHAiOjE2NzYzODM1NTF9.ZOkhQMj9P5uDHJhH09h8iOXN9rcXbMMwd-WghMmsrZk"
    //var accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0a2R3bHNAbmF2ZXIuY29tIiwiaWF0IjoxNjc2MzgxNjAxLCJleHAiOjE2NzY2ODE2MDF9.bjsbnNPSX5RdHtNxbrQ91wRhCXa5_5oeaa-BFis1I8M"
    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : ReservationAPI = retrofit.create(ReservationAPI::class.java)

    var roomId : Long = 1

    inner class ViewHolder (val binding : FragmentReservationBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun getItemCount(): Int {
        return 2 // Return the number of menus
    }

    private fun createFragment(position: Int): Fragment {
        Log.d("예약 화면 adapter", "dd")
        return when (position) {
            0 -> ReservationTabFragment()
            1 -> VisitedTabFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentReservationBinding.inflate(LayoutInflater.from(parent.context),parent,
            false
        )

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        createFragment(position = position)
    }
}