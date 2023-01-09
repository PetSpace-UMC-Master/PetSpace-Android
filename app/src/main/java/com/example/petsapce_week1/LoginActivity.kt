package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initData()
        //id password 임의로 설정
        val id: String = "a"
        val password: String = "a"

        //id, password check
        initFlag(id, password)

        //회원가입 화면으로 넘어감(채윤 화면)
        initNext()

    }


    /* private fun initData() {
         val id:String = "cw"
         val password:String = "abc"

     }*/

    fun initFlag(id: String, password: String) {
//        var flag: Boolean = false
        val severId = id
        val severPswd = password


        binding.apply {

            btnEmail.setOnClickListener {
                //edittext에서 값을 받아옴
                val inputEmail = editTextEmail.text.toString()
                val inputPassword = editTextPassword.text.toString()
                //저장된 id와 비번 맞는지 체크
                //맞으면 gif화면으로 이동(홈화면 넘어가야하는데 없어서 gif로 넘어감)
                if (severId == inputEmail && severPswd == inputPassword) {
                    val intent = Intent(this@LoginActivity, GifActivity::class.java)
                    startActivity(intent)
//                    flag = true

                }//틀리면 빨간글자 뜨게함
                else {
                    editTextEmail.setBackgroundResource(R.drawable.btn_custom_red)
                    editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                    textViewWarningRed.visibility = View.VISIBLE

                    /*editTextEmail.visibility = View.GONE
                    editTextPassword.visibility = View.GONE
                    editTextEmailRed.visibility = View.VISIBLE
                    editTextPasswordRed2.visibility = View.VISIBLE*/

                }

            }
        }

    }

    private fun initNext() {
        binding.apply {
            btnNewAccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, Signin2Activity::class.java)
                startActivity(intent)
            }
        }
    }
}