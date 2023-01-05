package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.petsapce_week1.databinding.ActivityRetryBinding

class RetryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this@RetryActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}