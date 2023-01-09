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

        //testcode
        //testcode12
        Glide.with(this).load(R.raw.petgif).override(560, 560).into(binding.imgGif)

    }
}