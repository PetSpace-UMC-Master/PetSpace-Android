package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivityPlacetogoBinding

class PlacetogoActivity : AppCompatActivity() {
    lateinit var binding : ActivityPlacetogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlacetogoBinding.inflate(layoutInflater)
        binding.imgJeju.setOnClickListener {
            val intent = Intent(this, JejuActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}