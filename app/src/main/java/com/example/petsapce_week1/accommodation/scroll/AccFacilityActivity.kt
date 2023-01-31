package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.accImgaeSlideAdapter
import com.example.petsapce_week1.accommodation.imageSlideData
import com.example.petsapce_week1.databinding.ActivityAccFacilityBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.AccommodationFacilityMore
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AccFacilityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAccFacilityBinding

    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        binding = ActivityAccFacilityBinding.inflate(layoutInflater)

        binding.tvViewmore.setOnClickListener {
            val intent = Intent(this, AccFacilityMoreActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}