package com.example.petsapce_week1.placetogo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import com.example.petsapce_week1.R
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.FavoriteBackendResponse
//import kotlinx.android.synthetic.main.placetogo_grid_itemlist.view.*
import retrofit2.Retrofit


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

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.placetogo_grid_itemlist, null)

        //view.menu_seoul.setImageResource(img_list[position])

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
            api.getFavorites(accessToken, "SEOUL", 0).enqueue(object : retrofit2.Callback<FavoriteBackendResponse>{
                override fun onResponse(
                    call: retrofit2.Call<FavoriteBackendResponse>,
                    response: retrofit2.Response<FavoriteBackendResponse>
                ) {
                    Log.d(" 함께 갈 곳에서 서울 버튼 누름", "ㅇㅇ")
                    Log.d("함께 갈 곳", response.body().toString())
                    val intent = Intent(context, TestSeoulAccommosFragment::class.java)
                    context.startActivity(intent)
                }

                override fun onFailure(
                    call: retrofit2.Call<FavoriteBackendResponse>,
                    t: Throwable
                ) {
                    Log.d(" 함께 갈 곳 호출 실패", "ㅠㅠ")
                }
            })
//

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