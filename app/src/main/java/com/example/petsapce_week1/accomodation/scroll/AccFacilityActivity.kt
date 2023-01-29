package com.example.petsapce_week1.accomodation.scroll

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivityAccFacilityBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelperHome
import retrofit2.Retrofit

class AccFacilityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAccFacilityBinding
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccFacilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewmore.setOnClickListener {
            val intent = Intent(this, AccFacilityMoreActivity::class.java)
            startActivity(intent)
        }
    }
}