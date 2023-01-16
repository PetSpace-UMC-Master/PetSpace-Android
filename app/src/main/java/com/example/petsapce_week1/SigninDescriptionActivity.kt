package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivitySigninDescriptionBinding
import com.example.petsapce_week1.loginrelated.LoginActivity

class SigninDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNext()
        initPrevious()
    }

    private fun initNext() {
        binding.apply {
            btnContinue.setOnClickListener {
                val intent = Intent(this@SigninDescriptionActivity,TermsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@SigninDescriptionActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}