package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import java.util.regex.Pattern

class Signin2Activity : AppCompatActivity() {
    lateinit var binding:ActivitySignin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        inintEmailCheck()
        initNext()
        initPrevious()


    }

    private fun inintEmailCheck() {
        val email = "aaa@naver.com"
        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        if (pattern.matcher(email).matches()) {
            //이메일 맞음!
            binding.textEmail.text = "사용 가능한 이메일입니다."
        } else {
            binding.textEmail.text = "사용 불가능한 이메일입니다."

        }



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