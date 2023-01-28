package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.accomodation.AccMainActivity
import com.example.petsapce_week1.databinding.ActivityHomeOnlyfortestBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.review.ReviewPostActivity

class TestMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeOnlyfortestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeOnlyfortestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccomo.setOnClickListener {
            val intent = Intent(this@TestMainActivity, AccMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@TestMainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnReviewCreate.setOnClickListener {
            val intent = Intent(this@TestMainActivity, ReviewPostActivity::class.java)
            startActivity(intent)

        }
    }
}