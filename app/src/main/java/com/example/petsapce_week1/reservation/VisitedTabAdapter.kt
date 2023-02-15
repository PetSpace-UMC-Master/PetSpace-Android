package com.example.petsapce_week1.reservation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.VisitedAccommoListBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.ReservationReadResponse
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import kotlinx.android.synthetic.main.visited_accommo_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class VisitedTabAdapter(val accessToken : String, var items: MutableList<ReservationReadResponse.Reservation>) :
    RecyclerView.Adapter<VisitedTabAdapter.ViewHolder>() {
    lateinit var binding : VisitedAccommoListBinding
    var roomId : Long = 1
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : AccomoService = retrofit.create(AccomoService::class.java)


    private val reservations = mutableListOf<ReservationReadResponse.Reservation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("예약 ?????",items.toString())
        binding = VisitedAccommoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("예약 왜 안될까?","1")

        return ViewHolder(binding)
    }

    fun setData(newReservations: ReservationReadResponse) {
        reservations.clear()
        reservations.addAll(newReservations.result.reservations)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: VisitedAccommoListBinding)
        : RecyclerView.ViewHolder(binding.root){
            @SuppressLint("SetTextI18n")
            fun bind(data: MutableList<ReservationReadResponse.Reservation>) {
                Log.d("예약 방문 완료 data", data.toString())
                val name = data[position].roomName
                val springDotsIndicator = binding.dotsIndicator
                val viewPager = binding.childViewPager

                val adapter = VisitedTabChildAdapter(data[position].roomImageUrls)
                viewPager.adapter = adapter
                springDotsIndicator.attachTo(viewPager)

                binding.apply {
                    tvAccommoName.text = name
                    tvReservationDate.text = "${data[position].startDate} ~ ${data[position].endDate}"
                }

                binding.btnHeartAfter.isSelected = true

                binding.apply {
                    topcardview.cardElevation = 0f
                    btnHeartAfter.setOnClickListener {
                        btnHeartAfter.isSelected = btnHeartAfter.isSelected != true
                        if(!btnHeartAfter.isSelected){

                            api.postLikes(accessToken, data[position].roomId).enqueue(object :
                                Callback<AccomodationData> {
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("방문 완료 좋아요", response.toString())
                                    Log.d("방문 완료 좋아요", response.body().toString())
                                }

                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                    Log.d("방문 완료 좋아요",t.toString())
                                }

                            })
                        }
                        else{
                            api.postLikes(accessToken, data[position].roomId).enqueue(object :
                                Callback<AccomodationData> {
                                override fun onResponse(
                                    call: Call<AccomodationData>,
                                    response: Response<AccomodationData>
                                ) {
                                    Log.d("방문 완료 좋아요", response.toString())
                                    Log.d("방문 완료 좋아요", response.body().toString())
                                }

                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                    Log.d("방문 완료 좋아요",t.toString())
                                }
                            })
                        }
                    }

                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val roomIDNext = items[position].result.reservations[position].roomId
//        roomId = items[position].result.reservations[position].roomId
//        Log.d("예약==roomIdNEXT", roomIDNext.toString())
//        Log.d("예약==roomId", roomId.toString())
//
//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context, AccMainActivity::class.java)
//            intent.putExtra("예약content",roomIDNext)
//            ContextCompat.startActivity(holder.itemView.context,intent,null)
//            Log.d("예약content",roomIDNext.toString())
//        }
//
        Log.d("예약 44334343", reservations.toString())
        holder.bind(reservations)
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

}