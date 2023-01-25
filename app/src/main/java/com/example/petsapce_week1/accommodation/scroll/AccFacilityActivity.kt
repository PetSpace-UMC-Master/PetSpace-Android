package com.example.petsapce_week1.accommodation.scroll

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.ActivityAccFacilityBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import retrofit2.Retrofit

class AccFacilityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAccFacilityBinding
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        binding = ActivityAccFacilityBinding.inflate(layoutInflater)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = AccFacilityMoreFragment()
        fragmentTransaction.add(R.id.activity_acc_main, fragment)
        fragmentTransaction.commit()

        binding.btnViewmore.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_acc_main, AccFacilityMoreFragment())
                .commit()
        }
        return binding.root
    }
}