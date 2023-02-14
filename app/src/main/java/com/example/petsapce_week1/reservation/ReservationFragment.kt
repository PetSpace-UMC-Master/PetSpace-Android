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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_reservation.view.*

class ReservationFragment : Fragment() {

    private lateinit var mContext: Context
    lateinit var binding : FragmentReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = context?.applicationContext!!
        binding = FragmentReservationBinding.inflate(layoutInflater)
        Log.d("예약 언제 실행되나1", "지금")
        //initViewPager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("예약 화면", "onViewCreated")

        val tabLayout = binding.reservTabLayout
        val viewPager = binding.reservViewpager

        viewPager.adapter = ReservationTabAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "예약 완료"
                1 -> tab.text = "방문 완료"
            }
        }.attach()
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