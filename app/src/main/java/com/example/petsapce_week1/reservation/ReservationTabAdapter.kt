package com.example.petsapce_week1.reservation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReservationTabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragments : ArrayList<ReservationTabFragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun addItems(fragment : ReservationTabFragment){
        fragments.add(fragment)
    }
}