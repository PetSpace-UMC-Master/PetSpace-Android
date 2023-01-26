package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.FragmentGoogleBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class googleFragment : Fragment() {

    private lateinit var viewModel: GoogleViewModel
    private lateinit var viewBinding:FragmentGoogleBinding

    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGoogleBinding.inflate(layoutInflater)

        //activity의 setcontentview가 아닌 return값을 주면된다.

        val roomId : Long = 2
        api.getRoomDetail(roomId = roomId).enqueue(object : Callback<AccomodationData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<AccomodationData>,
                response: Response<AccomodationData>
            ) {
                Log.d("숙소 세부 정보 google 통신 성공", response.toString())
                Log.d("숙소 세부 정보 google 통신 성공", response.body().toString())
                val body = response.body()
                if (body != null) {
                    //binding.frameHost.textName.text = body.result.hostName
                    viewBinding.textAddress.text = body.result.address
                    viewBinding.houseName.text = body.result.roomName
                }
            }

            override fun onFailure(call: Call<AccomodationData>, t: Throwable) {
                Log.d("숙소 세부 정보 google 통신 실패","ㅠㅠ")
            }
        })
        viewBinding.imgGoogle.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoogleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}