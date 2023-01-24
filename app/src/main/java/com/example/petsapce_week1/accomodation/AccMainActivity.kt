package com.example.petsapce_week1.accomodation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.accomodation.scroll.googleFragment
import com.example.petsapce_week1.accomodation.scroll.reviewFragment
import com.example.petsapce_week1.databinding.ActivityAccHostBinding
import com.example.petsapce_week1.databinding.ActivityAccMainBinding

class AccMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityAccMainBinding
    lateinit var bindingHostBinding: ActivityAccHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccMainBinding.inflate(layoutInflater)


        // .bind와 .inflate 차이 / layoutinflater , view 객체 차이
        val includeView: View = binding.frameHost.root
        bindingHostBinding = ActivityAccHostBinding.bind(includeView)


        // 이런식으로 간략하게 쳐도됨
//        bindingHostBinding = ActivityAccHostBinding.bind(binding.frameHost.root)


        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameGoogle.id, googleFragment())
            .commitAllowingStateLoss()

        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameReview.id, reviewFragment())
            .commitAllowingStateLoss()


    }
}