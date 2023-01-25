package com.example.petsapce_week1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}