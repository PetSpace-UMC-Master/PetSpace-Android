package com.example.petsapce_week1.reservation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.petsapce_week1.databinding.FragmentReservationBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import retrofit2.Retrofit

//viewpager adapter
class ReservationAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api: ReservationAPI = retrofit.create(ReservationAPI::class.java)

    var roomId: Long = 1

    inner class ViewHolder(val binding: FragmentReservationBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
    override fun getItemCount(): Int {
        return 2 // Return the number of menus
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReservationTabFragment()
            1 -> VisitedTabFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}