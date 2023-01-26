package com.example.petsapce_week1.accommodation.scroll

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.FragmentFacilitiesMoreBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import retrofit2.Retrofit

class AccFacilityMoreActivity : AppCompatActivity() {

    private lateinit var binding : FragmentFacilitiesMoreBinding
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFacilitiesMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFacilitesClose.setOnClickListener {
            val intent = Intent(this, AccMainActivity::class.java)
            startActivity(intent)
        }

    }
}