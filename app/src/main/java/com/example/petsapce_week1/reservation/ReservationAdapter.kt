package com.example.petsapce_week1.reservation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

//viewpager adapter
class ReservationAdapter(fragmentManager: Fragment) :
    FragmentStateAdapter(fragmentManager) {
    override fun getItemCount(): Int =2
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
