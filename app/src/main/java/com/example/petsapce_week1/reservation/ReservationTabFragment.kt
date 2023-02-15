package com.example.petsapce_week1.reservation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReservationTabBinding
import com.example.petsapce_week1.network.ReservationAPI
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.placetogo.NoPlaceToGoFragment
import com.example.petsapce_week1.placetogo.PlaceToGoFragment
import com.example.petsapce_week1.vo.ReservationReadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ReservationTabFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationTabAdapter: ReservationTabAdapter

//    companion object {
//        fun newInstance(accessToken: String): ReservationTabFragment {
//            val fragment = ReservationTabFragment()
//            val args = Bundle()
//            args.putString("accessToken", accessToken)
//            //args.putString("accommoList", accommoList)
//            fragment.arguments = args
//            return fragment
//        }
//    }

    //val data = arguments?.getParcelable<ReservationReadResponse.Reservation>("accommoList") // get the data class from the arguments bundle

    @SuppressLint("StaticFieldLeak")
    lateinit var binding : FragmentReservationTabBinding

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : ReservationAPI = retrofit.create(ReservationAPI::class.java)

    var roomId : Long = 1

    var accessToken : String ?= null

    private fun getAccessToken() {
        val atpref = requireContext().getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        accessToken = "Bearer $accessToken"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d("예약 완료 data", "$data")
//        val binding = FragmentReservationTabBinding.inflate(layoutInflater)
        //binding.recyclerviewReservationTab.adapter = ReservationTabAdapter.ViewHolder
        val view = inflater.inflate(R.layout.fragment_reservation_tab, container, false)

        getAccessToken()
        Log.d("예약 fragment onCreateView", "ddddd")

//        recyclerView = view.findViewById(R.id.recyclerview_reservation_tab)
//        reservationTabAdapter = accessToken?.let { ReservationTabAdapter(it) }!!
//        recyclerView.adapter = reservationTabAdapter

        Log.d("예약 fragment onCreateView", "ttt")

//        accessToken?.let { it ->
//            api.getReservationReadUpcoming(it, page = 0, size = 5).enqueue(object  : Callback<ReservationReadResponse>{
//                override fun onResponse(
//                    call: Call<ReservationReadResponse>,
//                    response: Response<ReservationReadResponse>
//                ) {
//                    Log.d("예약 완료 화면", response.toString())
//                    Log.d("예약 완료 화면", response.body().toString())
//                    if (response.isSuccessful) {
//                        val reservations = response.body()?.result
//                        reservations?.let {
//                            reservationTabAdapter.setData(it)
//                        }
//                    }
//                    else{
//                        val noplacetogofragment = NoPlaceToGoFragment()
//                        val placetogofragment = ReservationTabFragment()
//                        Log.d("함께 갈 곳 2222", "222222")
//                        placetogofragment.parentFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.thisLayout, noplacetogofragment)
//                            .addToBackStack(null)
//                            .commit()
//                    }
//                }
//
//                override fun onFailure(call: Call<ReservationReadResponse>, t: Throwable) {
//                    Log.d("예약 완료 실패", t.toString())
//                }
//
//            })
//        }

        return view
    }

}