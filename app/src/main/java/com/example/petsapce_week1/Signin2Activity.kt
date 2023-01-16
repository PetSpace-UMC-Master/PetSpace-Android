package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import java.util.regex.Pattern

class Signin2Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignin2Binding
    lateinit var passwordInput: String
    var flagEmail = 0
    var flagPassword = 0
    var flagEqual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        inintEmailCheck()

        var flag = checkEmail()
        Log.d("tagflag",flag.toString())
        initPasswordCheck()

        initPasswordEqual()



        Log.d("tag",flagEmail.toString())
        Log.d("tag2",flagPassword.toString())
        Log.d("tag2",flagEqual.toString())



        if (checkEmail() && checkPasswordEqual()) {

            initNext()
        }


    }

     fun initPasswordEqual() {

        binding.apply {

            editTextPasswordAgain.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // text가 변경된 후 호출
                    // s에는 변경 후의 문자열이 담겨 있다.
                    /*  passwordInput = editTextPassword.text.toString()
                      Log.d("tag", passwordInput)*/
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
                    checkPasswordEqual()
                }
            })

        }

    }

     fun initPasswordCheck() {
        binding.apply {

            editTextPassword.addTextChangedListener(object : TextWatcher {
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

                    checkPassword()
                    passwordInput = editTextPassword.text.toString()
                    Log.d("tag", passwordInput)
                }
            })

        }


    }

    //패스워드 체크
    fun checkPasswordEqual(): Boolean {
        binding.apply {

            var passwordEqual = passwordInput.toString().trim() //공백제거

            if (passwordEqual == editTextPasswordAgain.text.toString()) {

                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom)
                textPasswordAgain.text = " 비밀번호가 일치합니다."
                textPasswordAgain.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )

                //이메일 형태가 정상일 경우
//                    editTextEmail.setTextColor(R.color.black.toInt())
                return true
                flagEqual = 1
            } else {
                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                textPasswordAgain.text = "같은 비밀번호 형식을 입력하세요"
                textPasswordAgain.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                //또는 questionEmail.setTextColor(R.color.red.toInt())
                return false
            }
        }
    }


    //이메일 체크
    fun checkEmail(): Boolean {
        binding.apply {

            var email = editTextEmail.text.toString().trim() //공백제거
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
//                val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
            if (pattern.matcher(email).matches()) {

                editTextEmail.setBackgroundResource(R.drawable.btn_custom)
                textEmail.text = "사용 가능한 이메일 형식입니다."
                textEmail.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )

                return true
                flagEmail = 1
            } else {
                editTextEmail.setBackgroundResource(R.drawable.btn_custom_red)
                textEmail.text = "맞는 이메일 형식을 입력하세요"
                textEmail.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                //또는 questionEmail.setTextColor(R.color.red.toInt())
                return false
            }
        }
    }

    //비밀번호 체크
    fun checkPassword(): Boolean {
        binding.apply {

            var password = editTextPassword.text.toString().trim() //공백제거
            val pattern =
                Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$")

            val macher = pattern.matcher(password)
            if (macher.matches()) {

                editTextPassword.setBackgroundResource(R.drawable.btn_custom)
                textPassword.text = "사용 가능한 비밀번호 형식입니다."
                textPassword.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )

                return true
                flagPassword =1
            } else {
                editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                textPassword.text = "맞는 비밀번호 형식을 입력하세요"
                textPassword.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                //또는 questionEmail.setTextColor(R.color.red.toInt())
                return false
            }
        }
    }

     fun inintEmailCheck() {

        //textwathcher
        binding.apply {

            //1. 이메일
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
            btnContinueAfter.setOnClickListener {
                val intent = Intent(this@Signin2Activity, SigninDescriptionActivity::class.java)
                startActivity(intent)
            }
        }
    }


}