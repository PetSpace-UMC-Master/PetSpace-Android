package com.example.petsapce_week1.accommodation.scroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivityHostMoreBinding

class AccHostViewMoreActivity :AppCompatActivity() {
    private lateinit var binding: ActivityHostMoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvHostname.text = "${intent.getStringExtra("name")}님의 숙소"
        binding.btnHostClose.setOnClickListener {
            finish()
        }
    }
}