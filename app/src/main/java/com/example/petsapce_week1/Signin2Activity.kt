package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import java.util.regex.Pattern

class Signin2Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignin2Binding
    var passwordFlag = true
    var passwordAgainFlag = true
    var emailFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // == Email ==
        binding.apply {
            editTextEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    initEmailCheck()
                }
            })
        }

        binding.editTextPassword.addTextChangedListener(passwordListener)
        binding.editTextPasswordAgain.addTextChangedListener(passwordAgainListener)

        Log.d("text 2222", "${passwordAgainFlag}, ${passwordFlag}, ${emailFlag}")

        // == 이메일 중복 확인 버튼 ==
        if(emailFlag){
            binding.emailDuplicationAfter.visibility = View.VISIBLE
            Log.d("text", "중복 버튼 떠야지")
            //중복 아님 확인되면 버튼 비활성화 + 입력된 이메일 고정
            binding.emailDuplicationAfter.setOnClickListener {
                //emailDuplicationAfter.visibility = View.INVISIBLE
                //editTextEmail.focusable(false)
                binding.textEmail.text = "사용 가능한 이메일입니다."
                binding.textEmail.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )
            }
        }
        binding.apply {
            if (passwordFlag && passwordAgainFlag && emailFlag) {
                Log.d("text","깃발 다 올려짐 버튼 떠야지")
                btnContinueAfter.visibility = View.VISIBLE
                btnContinueAfter.setOnClickListener {
                    val intent =
                        Intent(this@Signin2Activity, SigninDescriptionActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun initEmailCheck() {
        //textwathcher
        binding.apply {
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            var email = editTextEmail.text.toString().trim() //공백제거

            if (pattern.matcher(email).matches()) {
                editTextEmail.setBackgroundResource(R.drawable.btn_emailbox)
                textEmail.text = "사용 가능한 이메일 형식입니다."
                emailDuplicationAfter.visibility = View.VISIBLE
                textEmail.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )
                emailFlag = true
                Log.d("text","이메일 같음, ${emailFlag}")
                //이메일 형태가 정상일 경우
//                    editTextEmail.setTextColor(R.color.black.toInt())
            } else {
                editTextEmail.setBackgroundResource(R.drawable.btn_emailbox_error)
                textEmail.text = "올바른 이메일 형식을 입력하세요"
                textEmail.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.red
                    )
                )
                //emailDuplicationAfter.visibility = View.INVISIBLE
                //또는 questionEmail.setTextColor(R.color.red.toInt())
            }
        }
    }
    fun passwordRegex(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$".toRegex())
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
                        passwordFlag = false
                        binding.apply {
                            editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                            textPassword.text = "영문, 숫자, 특수문자 포함 8자 이상을 입력해주세요"
                            textPassword.setTextColor(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.red
                                )
                            )
                        }
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
                    }
                }
            }
        }
    }


    private val passwordAgainListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.apply {

                if(editTextPassword.text.toString() == editTextPasswordAgain.text.toString()){
                    editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom)
                    textPasswordAgain.text = null
                    passwordAgainFlag = true
                }
                else{
                    editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                    textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요"
                    textPasswordAgain.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                    passwordAgainFlag = false
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        passwordAgainFlag = false
                        binding.apply {
                            editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                            textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요."
                            textPasswordAgain.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                        }
                    }
                    s.isNotEmpty() -> {
/*                                when {
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
                                }*/
                        binding.apply {

                            if(editTextPassword.text.toString() == editTextPasswordAgain.text.toString()){
                                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom)
                                textPasswordAgain.text = null
                                passwordAgainFlag = true

                            }
                            else{
                                passwordAgainFlag = false
                                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                                textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요"
                                textPasswordAgain.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                            }
                        }
                    }
                }
            }
        }
    }
}


