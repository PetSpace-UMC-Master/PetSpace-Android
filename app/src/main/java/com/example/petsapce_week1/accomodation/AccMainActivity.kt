package com.example.petsapce_week1.accomodation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityAccHostBinding
import com.example.petsapce_week1.databinding.ActivityAccMainBinding
import com.example.petsapce_week1.databinding.ActivityGifBinding

class AccMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityAccMainBinding




    lateinit var binding2:ActivityGifBinding

    lateinit var accHostBinding: ActivityAccHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccMainBinding.inflate(layoutInflater)


//        val includeView: View = binding.frame1.root
//        binding2 = ActivityGifBinding.bind(includeView)


//        accHostBinding = ActivityAccHostBinding.bind(binding.frame2.root)


        setContentView(binding.root)


     /*   Glide.with(this).load(R.raw.petgif).override(560, 560).into(binding2.imgGif)
        binding2.hello.text = "수정반영"
        accHostBinding.button.setOnClickListener {
            var intent = Intent(this,GifActivity::class.java)
            startActivity(intent)
        }*/

    }
}