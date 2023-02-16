package com.example.petsapce_week1.reservationbcw

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainVPAdapter(fr: Fragment):FragmentStateAdapter(fr) {
    override fun getItemCount(): Int=2

    override fun createFragment(position: Int): Fragment { //포지션에 따라 어떤 프레그먼트를 보여줄것인지
        return when(position){
            0-> LeftFragment()
            1-> RightFragment()
            else -> LeftFragment()
        }
    }
}