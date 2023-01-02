package com.example.petsapce_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.ActivityGifBinding

class GifActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGifBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.raw.catgif).override(560, 560).into(binding.imgGif)

        binding.hello.text = "aaaaaaa"


    }
}