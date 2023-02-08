package com.example.petsapce_week1.placetogo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import com.example.petsapce_week1.R
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FacilityData
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.FavoriteData
import kotlinx.android.synthetic.main.placetogo_grid_itemlist.view.*
import retrofit2.Retrofit
import java.io.Serializable


class PlaceGridAdapter(val context: Context, var img_list: Array<Int>, val accessToken : String) : BaseAdapter() {

    override fun getCount(): Int {
        return img_list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    lateinit var postaccessToken : String

    val accommoList = mutableListOf<FavoriteData>()

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.placetogo_grid_itemlist, null)

        view.menu_seoul.setImageResource(img_list[position])

        val button = view.findViewById<ImageButton>(R.id.menu_seoul)
        button.setOnClickListener {

//            api.postLikes(accessTokenPost).enqueue(object : retrofit2.Callback<AccomodationData> {
//                override fun onResponse(
//                    call: retrofit2.Call<AccomodationData>,
//                    response: retrofit2.Response<AccomodationData>
//                ) {
//                    Log.d("숙소 좋아요 표시", "했음")
//                }
//
//                override fun onFailure(call: retrofit2.Call<AccomodationData>, t: Throwable) {
//                    Log.d("숙소 좋아요 표시", "x했음")
//                }
//
//            })
            Log.d("함께 갈 곳 토큰 받아와55", accessToken)
            postaccessToken = "Bearer $accessToken"
            Log.d("함께 갈 곳 토큰 받아와11", postaccessToken)


            api.getFavorites(postaccessToken, "SEOUL", 1,3).enqueue(object : retrofit2.Callback<FavoriteBackendResponse>{
                override fun onResponse(
                    call: retrofit2.Call<FavoriteBackendResponse>,
                    response: retrofit2.Response<FavoriteBackendResponse>
                ) {
                    Log.d(" 함께 갈 곳에서 서울 버튼 누름", "ㅇㅇ")
                    Log.d("함께 갈 곳 성공", response.toString())
                    Log.d("함께 갈 곳", response.body().toString())

                    val intent = Intent(context, TestSeoulAccommosFragment::class.java)

                    response.takeIf { it.isSuccessful }
                        ?.body()
                        .let {
                            if(it != null){
                                for(item in it.result.favorites){
                                    accommoList.apply {
                                        add(
                                            FavoriteData(
                                                availableDays = item.availableDays,
                                                averageReviewScore = item.averageReviewScore,
                                                id = item.id,
                                                numberOfReview = item.numberOfReview,
                                                price = item.price,
                                                roomAddress = item.roomAddress,
                                                roomImages = item.roomImages.subList(0, item.roomImages.size)
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    //intent.putExtra("accommoList", accommoList[position])
                    //val list = listOf("item1", "item2", "item3")
//                    val bundle = Bundle()
//                    bundle.putStringArrayList("list_data", accommoList)
                    //val intent = Intent(this, SecondActivity::class.java)
                    //intent.putExtra(accommoList)
                    context.startActivity(intent)
                }

                override fun onFailure(
                    call: retrofit2.Call<FavoriteBackendResponse>,
                    t: Throwable
                ) {
                    Log.d(" 함께 갈 곳 호출 실패", "ㅠㅠ")
                    Log.d("함께 에러 메시지", t.toString())
                }
            })

//      프래그먼트로 이동 시
//            val newFragment = NewFragment.newInstance(getItem(position) as String)
//            fragment.parentFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_container, newFragment)
//                .addToBackStack(null)
//                .commit()
        }
        return view
    }


}