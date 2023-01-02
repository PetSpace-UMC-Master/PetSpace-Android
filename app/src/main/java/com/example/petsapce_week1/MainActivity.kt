package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNext()


    }

    private fun initNext() {
        binding.apply {
            btnEmail.setOnClickListener {
                val intent = Intent(this@MainActivity,GifActivity::class.java)
                startActivity(intent)
            }
        }
    }
}