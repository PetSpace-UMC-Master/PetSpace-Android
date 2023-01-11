package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import java.util.regex.Pattern

class Signin2Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)




        inintEmailCheck()
        initNext()
        initPrevious()


    }

    private fun inintEmailCheck() {
//        val email = "aaa@naver.com"
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        /*      if (pattern.matcher(email).matches()) {
                  //이메일 맞음!
                  binding.textEmail.text = "사용 가능한 이메일입니다."
              } else {
                  binding.textEmail.text = "사용 불가능한 이메일입니다."

              }*/

        fun checkEmail(): Boolean {
            binding.apply {

                var email = editTextEmail.text.toString().trim() //공백제거
                val pattern: Pattern = Patterns.EMAIL_ADDRESS
                val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
                if (pattern.matcher(email).matches()) {

                    editTextEmail.setBackgroundResource(R.drawable.btn_custom)
                    textEmail.text = "사용 가능한 이메일 형식입니다."
                    textEmail.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.red))

                    //이메일 형태가 정상일 경우
//                    editTextEmail.setTextColor(R.color.black.toInt())
                    return true
                } else {
                    editTextEmail.setBackgroundResource(R.drawable.btn_custom_red)
                    textEmail.text = "맞는 이메일 형식을 입력하세요"
                    textEmail.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.red))
                    //또는 questionEmail.setTextColor(R.color.red.toInt())
                    return false
                }
            }
        }

        binding.apply {

            editTextEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // text가 변경된 후 호출
                    // s에는 변경 후의 문자열이 담겨 있다.
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // text가 변경되기 전 호출
                    // s에는 변경 전 문자열이 담겨 있다.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // text가 바뀔 때마다 호출된다.
                    // 우린 이 함수를 사용한다.
                    checkEmail()
                }
            })

        }

    }

    private fun initNext() {
        binding.apply {
            btnContinue.setOnClickListener {
                val intent = Intent(this@Signin2Activity, SigninDescriptionActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@Signin2Activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}