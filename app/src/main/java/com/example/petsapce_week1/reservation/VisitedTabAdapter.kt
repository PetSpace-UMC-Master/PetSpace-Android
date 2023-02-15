package com.example.petsapce_week1.reservation

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.VisitedAccommoListBinding
import com.example.petsapce_week1.vo.ReservationReadResponse
import kotlinx.android.synthetic.main.visited_accommo_list.view.*

class VisitedTabAdapter(var items: MutableList<ReservationReadResponse>) :
    RecyclerView.Adapter<VisitedTabAdapter.ViewHolder>() {
    lateinit var binding : VisitedAccommoListBinding
    var roomId : Long = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = VisitedAccommoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("예약 왜 안될까?","1")
        return ViewHolder(binding.root)
    }
//inner class ViewHolder(val binding: PlacetogoItemsBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
//        fun bind(data: FavoriteBackendResponse.Favorite) {
//            Log.d("함께 갈 곳 data", data.toString())

    inner class ViewHolder(val binding: LinearLayout)
        : RecyclerView.ViewHolder(binding){
            @SuppressLint("SetTextI18n")
            fun bind(data: ReservationReadResponse) {
                Log.d("예약 방문 완료 data", data.toString())
                val name = data.result.reservations[position].roomName
                val springDotsIndicator = binding.dots_indicator
                val viewPager = binding.childViewPager
                val adapter = VisitedTabChildAdapter(data.result.reservations[position].roomImageUrls)
                viewPager.adapter = adapter
                springDotsIndicator.attachTo(viewPager)

                binding.apply {
                    tv_accommo_name.text = name
                    tv_reservation_date.text = "${data.result.reservations[position].startDate} ~ ${data.result.reservations[position].endDate}"
                }

                binding.btn_heart_after.isSelected = true

//                binding.apply {
//                    topcardview.cardElevation = 0f
//                    btn_heart_after.setOnClickListener {
//                        btn_heart_after.isSelected = btn_heart_after.isSelected != true
//                        if(!btn_heart_after.isSelected){
//                            api.postLikes(accessToken, data.id.toLong()).enqueue(object :
//                                Callback<AccomodationData> {
//                                override fun onResponse(
//                                    call: Call<AccomodationData>,
//                                    response: Response<AccomodationData>
//                                ) {
//                                    Log.d("숙소 좋아요 표시", "했음")
//                                    Log.d("숙소 좋아요 roomId", data.id.toLong().toString())
//                                }
//
//                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
//                                    Log.d("숙소 좋아요 표시", "x했음")
//                                }
//
//                            })
//                        }
//                        else{
//                            api.postLikes(accessToken, data.id.toLong()).enqueue(object :
//                                Callback<AccomodationData> {
//                                override fun onResponse(
//                                    call: Call<AccomodationData>,
//                                    response: Response<AccomodationData>
//                                ) {
//                                    Log.d("숙소 좋아요 표시", "뺐음")
//                                    Log.d("roomId", data.id.toLong().toString())
//                                }
//
//                                override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
//                                    Log.d("숙소 좋아요 표시", "x했음")
//                                }
//
//                            })
//                        }
//                    }
//
//                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roomIDNext = items[position].result.reservations[position].roomId
        roomId = items[position].result.reservations[position].roomId
        Log.d("예약==roomIdNEXT", roomIDNext.toString())
        Log.d("예약==roomId", roomId.toString())

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AccMainActivity::class.java)
            intent.putExtra("예약content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("예약content",roomIDNext.toString())
        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}