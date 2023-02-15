package com.example.petsapce_week1.reservation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.ReservationAccommoListBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import kotlinx.android.synthetic.main.visited_accommo_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReservationTabAdapter(val accessToken : String, private val reservations : MutableList<ReservationReadResponse.Reservation>) : RecyclerView.Adapter<ReservationTabAdapter.ReservationViewHolder>() {
    lateinit var binding : ReservationAccommoListBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        binding = ReservationAccommoListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        Log.d("예약 onCreateViewHolder", reservations.toString())
        //ReservationViewHolder(binding).bind(reservations)
        return ReservationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        Log.d("예약 bindViewHolder", reservations.toString())
        holder.bind(reservations[position])
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newReservations: ReservationReadResponse.Result) {
        reservations.clear()
        reservations.addAll(newReservations.reservations)
        notifyDataSetChanged()
    }

    inner class ReservationViewHolder(val binding : LinearLayout) : RecyclerView.ViewHolder(binding) {

        fun bind(data: ReservationReadResponse.Reservation) {
            Log.d("예약 방문 완료 data", data.toString())
            val name = data.roomName
            val springDotsIndicator = binding.dots_indicator
            val viewPager = binding.childViewPager
            val adapter =
                VisitedTabChildAdapter(data.roomImageUrls)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)

            binding.apply {
                tv_accommo_name.text = name
                tv_reservation_date.text =
                    "${data.startDate} ~ ${data.endDate}"
            }

            binding.btn_heart_after.isSelected = true

            binding.apply {
                topcardview.cardElevation = 0f
                btn_heart_after.setOnClickListener {
                    btn_heart_after.isSelected = btn_heart_after.isSelected != true
                    if (!btn_heart_after.isSelected) {

                        api.postLikes(accessToken, data.roomId)
                            .enqueue(object :
                                Callback<AccomodationData> {
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("예약 완료 좋아요", response.toString())
                                    Log.d("예약 완료 좋아요", response.body().toString())
                                }

                                override fun onFailure(
                                    call: Call<AccomodationData>,
                                    t: Throwable
                                ) {
                                    Log.d("예약 완료 좋아요", t.toString())
                                }

                            })
                    } else {
                        api.postLikes(accessToken, data.roomId)
                            .enqueue(object :
                                Callback<AccomodationData> {
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("예약 완료 좋아요 취소", response.toString())
                                    Log.d("예약 완료 좋아요 ㅊ", response.body().toString())
                                }

                                override fun onFailure(
                                    call: Call<AccomodationData>,
                                    t: Throwable
                                ) {
                                    Log.d("예약 완료 좋아요 ㅊ", t.toString())
                                }
                            })
                    }
                }

            }
        }
    }
}
