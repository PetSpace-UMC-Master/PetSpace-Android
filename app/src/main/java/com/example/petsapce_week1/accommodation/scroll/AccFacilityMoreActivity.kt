package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.FragmentFacilitiesMoreBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.AccommodationFacilityMore
import com.example.petsapce_week1.vo.FacilityData
import com.example.petsapce_week1.vo.FacilityReceived
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.security.AccessController.getContext

class AccFacilityMoreActivity : AppCompatActivity() {

    private lateinit var binding : FragmentFacilitiesMoreBinding
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)
    val facilityReceivedList = mutableListOf<FacilityReceived>()

    var facilityReceived = mutableListOf<FacilityData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFacilitiesMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFacilitesClose.setOnClickListener {
            val intent = Intent(this, AccMainActivity::class.java)
            startActivity(intent)
        }
        //홈화면 리사이클러뷰에서 넘어감
        val roomId  = intent.getIntExtra("content",-1)

        api.getFacilities(roomId = roomId.toLong()).enqueue(object : Callback<AccommodationFacilityMore> {
            @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
            override fun onResponse(
                call: Call<AccommodationFacilityMore>,
                response: Response<AccommodationFacilityMore>
            ) {
                Log.d("숙소 facility 통신 성공1", response.toString())
                Log.d("숙소 facility 통신 성공1", response.body().toString())
                val body = response.body()

                response.takeIf { it.isSuccessful }
                    ?.body()
                    .let { it ->
                        Log.d("숙소 it 값 : ", "${it}")
                        if (it != null) {
                            for (item in it.result.allFacilityInfos) {
                                // item : 하나의 카테고리 + 여러 개의 편의시설들
                                facilityReceivedList.apply {
                                    add(
                                        FacilityReceived(
                                            category = item.category,
                                            facilities = item.facilities
                                        )
                                    )
                                }
                            }
                            binding.rvCategory.adapter = AccFacilitiesCategoryAdapter(facilityReceivedList)
                            binding.rvCategory.layoutManager = LinearLayoutManager(this@AccFacilityMoreActivity)
                        }
                    }
            }
            override fun onFailure(call: Call<AccommodationFacilityMore>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}