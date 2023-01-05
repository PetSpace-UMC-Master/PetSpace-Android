package com.example.petsapce_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivityRetryBinding
import com.example.petsapce_week1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}