package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivityLoginBinding

class LoginFailActivity : AppCompatActivity(){
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initNext()


    }

   /* private fun initNext() {
        binding.apply {
            btnNewAccount.setOnClickListener {
                val intent = Intent(this@LoginFailActivity,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }*/
}