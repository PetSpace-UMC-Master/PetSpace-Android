package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivitySignin2Binding

class Signin2Activity : AppCompatActivity() {
    lateinit var binding:ActivitySignin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initNext()
        initPrevious()


    }



    private fun initNext() {
        binding.apply {
            btnContinue.setOnClickListener {
                val intent = Intent(this@Signin2Activity,SigninDescriptionActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@Signin2Activity,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}