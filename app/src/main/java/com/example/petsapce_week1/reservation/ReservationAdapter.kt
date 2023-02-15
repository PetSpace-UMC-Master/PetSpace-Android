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
import com.example.petsapce_week1.home.HomeResOneFragment
import com.example.petsapce_week1.home.HomeResTwoFragment
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import retrofit2.Retrofit

//viewpager adapter
class ReservationAdapter(fragmentManager: FragmentManager) :
    FragmentStateAdapter(fragmentManager) {
    /*override fun getItemCount(): Int {
        return 2
    }*/

    /* private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
     var api: ReservationAPI = retrofit.create(ReservationAPI::class.java)

     var roomId: Long = 1

     inner class ViewHolder(val binding: FragmentReservationBinding) :
         RecyclerView.ViewHolder(binding.root) {

     }
     override fun getItemCount(): Int {
         return 2 // Return the number of menus
     }*/

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReservationTabFragment()
            1 -> VisitedTabFragment()
            else -> ReservationTabFragment()
        }
    }
}
/*

class HomeResVPAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int=2

    override fun createFragment(position: Int): Fragment { //포지션에 따라 어떤 프레그먼트를 보여줄것인지
        return when(position){
            0-> HomeResOneFragment()
            1-> HomeResTwoFragment()
            else -> HomeResOneFragment()
        }
    }


}*/
