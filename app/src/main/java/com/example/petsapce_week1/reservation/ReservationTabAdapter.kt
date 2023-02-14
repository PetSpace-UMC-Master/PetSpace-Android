package com.example.petsapce_week1.reservation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReservationTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // Return the number of menus
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("예약 화면 adapter", "dd")
        return when (position) {
            0 -> ReservationTabFragment()
            1 -> VisitedTabFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}