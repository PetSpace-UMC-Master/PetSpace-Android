package com.example.petsapce_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivitySigninDescriptionBinding

class SigninDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}