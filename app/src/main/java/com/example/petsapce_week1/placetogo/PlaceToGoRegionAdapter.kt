package com.example.petsapce_week1.placetogo

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.PlacetogoItemsBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.FavoriteData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.DecimalFormat

class PlaceToGoRegionAdapter(var items: MutableList<FavoriteBackendResponse.Favorite>, val accessToken : String) : RecyclerView.Adapter<PlaceToGoRegionAdapter.ViewHolder>() {

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    // 좋아요 버튼 눌렀을 때 호출
    var apiLike : AccomoService = retrofit.create(AccomoService::class.java)

    var roomId : Long = 1

    interface OnItemClickListener {
        fun OnItemClick(data: FavoriteData)
//        fun onClick(v: View, position: Int)
    }
    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: PlacetogoItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: FavoriteBackendResponse.Favorite) {
            Log.d("함께 갈 곳 data", data.toString())
            //val cut = String.format("%.2f", data.averageReviewScore)
            val priceCut = DecimalFormat("#,###")
            var price = priceCut.format(data.price)
            Log.d("함께 갈 곳 data", data.toString())
            val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = PlaceToGoChildAdapter(data.roomImages)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)
            Log.d("함께 갈 곳 data", data.toString())

            binding.apply {
                textPrice.text = "₩${price} / 박"
                textLoc.text = data.roomAddress
                textScore.text = data.averageReviewScore.toString()
                textReview.text = data.numberOfReview.toString()
            }
            /*      val springDotsIndicator = binding.dotsIndicator
                  val viewPager = binding.childViewPager
                  val adapter = HomeChildAdapter(items[position].imgList)
                  viewPager.adapter = adapter
                  springDotsIndicator.attachTo(viewPager)*/
            binding.btnHeartAfter.isSelected = true

            binding.apply {
                topcardview.cardElevation = 0f
                btnHeartAfter.setOnClickListener {
                    btnHeartAfter.isSelected = btnHeartAfter.isSelected != true
                    if(!btnHeartAfter.isSelected){
                        api.postLikes(accessToken, data.id.toLong()).enqueue(object : Callback<AccomodationData> {
                            override fun onResponse(
                                call: Call<AccomodationData>,
                                response: Response<AccomodationData>
                            ) {
                                Log.d("숙소 좋아요 표시", "했음")
                                Log.d("숙소 좋아요 roomId", data.id.toLong().toString())
                            }

                            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                Log.d("숙소 좋아요 표시", "x했음")
                            }

                        })
                    }
                    else{
                        api.postLikes(accessToken, data.id.toLong()).enqueue(object : Callback<AccomodationData> {
                            override fun onResponse(
                                call: Call<AccomodationData>,
                                response: Response<AccomodationData>
                            ) {
                                Log.d("숙소 좋아요 표시", "뺐음")
                                Log.d("roomId", data.id.toLong().toString())
                            }

                            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                                Log.d("숙소 좋아요 표시", "x했음")
                            }

                        })
                    }
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.d("함께 갈 곳 items", "$items")
        val binding =
            PlacetogoItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //child recyclerview
//        holder.binding.childRecyclerView.adapter = HomeChildAdapter(items[position].imgList)

        /*   val restadapter = HomeChildAdapter(items[position].imgList)
           holder.binding.childViewPager.adapter = restadapter

           holder.binding.childViewPager.adapter = HomeChildAdapter(items[position].imgList)*/

        /*   val springDotsIndicator = holder.binding.dotsIndicator
           val viewPager = holder.binding.childViewPager
           val adapter = HomeChildAdapter(items[position].imgList)
           viewPager.adapter = adapter
           springDotsIndicator.attachTo(viewPager)*/

        val roomIDNext = items[position].id
        roomId = items[position].id.toLong()
        Log.d("==roomIdNEXT", roomIDNext.toString())
        Log.d("==roomId", roomId.toString())

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AccMainActivity::class.java)
            intent.putExtra("content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("content",roomIDNext.toString())
        }

        holder.bind(items[position])

    }


    override fun getItemCount(): Int {
        return items.size
    }

    /* fun updateItems(newItems: HomeResponse) {
         items.clear()
         items.addAll(newItems)
         notifyDataSetChanged()
     }*/


}

