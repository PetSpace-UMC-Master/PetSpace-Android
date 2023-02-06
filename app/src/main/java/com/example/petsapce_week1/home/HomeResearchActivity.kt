package com.example.petsapce_week1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHomeResearchBinding

class HomeResearchActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeResearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeResearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}