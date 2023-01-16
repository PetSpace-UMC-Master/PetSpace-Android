package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import com.example.petsapce_week1.loginrelated.LoginActivity
import java.util.regex.Pattern


        class Signin2Activity : AppCompatActivity() {
            lateinit var binding: ActivitySignin2Binding
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivitySignin2Binding.inflate(layoutInflater)
                setContentView(binding.root)


                initEmailCheck()
                initNext()
                initPrevious()


                //initPwdCheck()

                binding.editTextPassword.addTextChangedListener(passwordListener)
                binding.editTextPasswordAgain.addTextChangedListener(passwordAgainListener)

            }

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

                        //이메일 형태가 정상일 경우
//                    editTextEmail.setTextColor(R.color.black.toInt())
                        return true
                    } else {
                        editTextEmail.setBackgroundResource(R.drawable.btn_custom_red)
                        textEmail.text = "올바른 이메일 형식을 입력하세요"
                        textEmail.setTextColor(
                            ContextCompat.getColor(
                                applicationContext!!,
                                R.color.red
                            )
                        )
                        //또는 questionEmail.setTextColor(R.color.red.toInt())
                        return false
                    }
                }
            }

            private fun initEmailCheck() {
//        val email = "aaa@naver.com"
                val emailValidation =
                    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                val pattern: Pattern = Patterns.EMAIL_ADDRESS


                // !!!!이메일 중복 체크 추가 구현 필요
                /*      if (pattern.matcher(email).matches()) {
                      //이메일 맞음!
                      binding.textEmail.text = "사용 가능한 이메일입니다."
                  } else {
                      binding.textEmail.text = "사용 불가능한 이메일입니다."
                  }*/


                //textwathcher
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

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
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
                        val intent =
                            Intent(this@Signin2Activity, SigninDescriptionActivity::class.java)
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


            // 비밀번호 조건 체크 (특수문자 반드시 포함, 7자리 이상)
            fun passwordRegex(password: String): Boolean {
                return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{7,}$".toRegex())
            }


            var passwordFlag = false
            var passwordAgainFlag = false

            fun flagCheck() {
                binding.apply {
                    btnContinue.isEnabled = passwordFlag && passwordAgainFlag
                }
                //binding.btnContinue.isEnabled =passwordFlag
                //binding.btnContinue.isEnabled = emailFlag && passwordFlag && passwordCheckFlag
            }

            private val passwordListener = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null) {
                        when {
                            s.isEmpty() || !passwordRegex(s.toString()) -> {
                                binding.apply {
                                    editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                                    textPassword.text = "영문, 숫자, 특수문자 포함 7자 이상을 입력해주세요"
                                    textPassword.setTextColor(
                                        ContextCompat.getColor(
                                            applicationContext!!,
                                            R.color.red
                                        )
                                    )
                                }
                                passwordFlag = false
                            }

                            else -> {
                                binding.apply {
                                    textPassword.text = "사용 가능한 비밀번호입니다"
                                    textPassword.setTextColor(
                                        ContextCompat.getColor(
                                            applicationContext!!,
                                            R.color.main_green
                                        )
                                    )
                                    editTextPassword.setBackgroundResource(R.drawable.btn_custom)
                                }
                                passwordFlag = true
                            }
                        }
                    }
                }
            }


            private val passwordAgainListener = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null) {
                        when {
                            s.isEmpty() -> {
                                binding.apply {
                                    editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                                    textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요."
                                }
                                passwordAgainFlag = false
                            }
                            s.isNotEmpty() -> {
                                when {
                                    binding.editTextPassword.toString() != binding.editTextPasswordAgain.toString() -> {
                                        binding.apply {
                                            editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                                            textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요"
                                            textPasswordAgain.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                                        }
                                        passwordAgainFlag = false
                                    }

                                    else -> {
                                        binding.apply {
                                            editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom)
                                            textPasswordAgain.text = null
                                        }
                                        passwordAgainFlag = true
                                    }
                                }
                            }
                        }
                        flagCheck()
                    }
                }
            }
        }


//    void checkPwd(String password) {
//        val val_symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])"
//
//        Pattern pattern_alpha = Pattern.
//
//    }
//
//    fun checkPwd(): Boolean {
//        binding.apply {
//
//            //editTextPassword.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->  })
//            var pwd = editTextPassword.text.toString().trim() //공백제거
//            //val pattern: Pattern = Patterns.EMAIL_ADDRESS
//            val pwPattern = "^(?=.*[A-Za-z])(?=.*[$@$!%*"
//            if (pattern.matcher(pwPattern, pwd).matches()) {
//
//                editTextPassword.setBackgroundResource(R.drawable.btn_custom)
//                textPassword.text = "사용 가능한 비밀번호입니다."
//                textPassword.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.main_green))
//
//                return true
//            } else {
//                editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
//                textPassword.text = "특수문자 포함 7자 이상 입력해야 합니다."
//                textPassword.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.red))
//                return false
//            }
//        }
//    }
