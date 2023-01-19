package com.example.petsapce_week1.accomodation.scroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivityAccHostBinding

class AccHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccHostBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}