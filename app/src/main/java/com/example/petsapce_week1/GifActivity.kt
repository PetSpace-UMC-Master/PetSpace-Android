package com.example.petsapce_week1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.ActivityGifBinding
import com.example.petsapce_week1.databinding.ActivityLoginBinding

class GifActivity:AppCompatActivity() {
    private lateinit var binding: ActivityGifBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //gif를 한프레임이 아닌 계속된 프레임으로 움직이게 하는 코드.
        Glide.with(this).load(R.raw.petgif).override(560, 560).into(binding.imgGif)

    }
}