package com.example.petsapce_week1.accomodation.scroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.databinding.ActivityAccHostBinding
import com.example.petsapce_week1.loginrelated.LoginBackendResponse
import com.example.petsapce_week1.loginrelated.UserModelGeneral
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AccHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccHostBinding
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // == 백엔드 통신 부분 ==
        val data = AccomodationRoomData(roomId = null)



        api.getRoomDetail(data).enqueue(object : Callback<AccomodationData>{
            override fun onResponse(
                call: Call<AccomodationData>,
                response: Response<AccomodationData>
            ) {

                Log.d("숙소 세부 정보 통신 성공",response.toString())
                Log.d("숙소 세부 정보 통신 성공", response.body().toString())

                val body = response.body()
                if (body != null) {
                    binding.textName.text = body.result.hostName
                    binding.tvMaxguest.text = body.result.maxGuest.toString()
                    binding.tvMaxpet.text = body.result.maxPet.toString()

                }
            }
            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                Log.d("숙소 세부 정보", "failed")
            }
        })
    }
}