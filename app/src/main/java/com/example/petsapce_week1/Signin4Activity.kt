package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.ActivitySignin2Binding
import java.util.regex.Pattern

class Signin4Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignin2Binding
    lateinit var viewModel: EmailViewModel


    //비밀번호 입력 담는 지역 변수(올바른 형식으로 초기화 안할시 처음 화면에서 빨간불 들어옴)
    lateinit var passwordInput: String

    var flagEmail = 0
    var flagPassword = 0
    var flagEqual = 0
    var flagButton = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmailViewModel::class.java)


        //순서대로 실행(별 상관없는듯함)

        //1번 이메일 체크
        inintEmailCheck()


        //1-1 이메일 중복 체크
        //임의의 이메일 선언
        val email = "aaa@naver.com"
        initButtonCheck(email)

        //2번 패스워드 체크
        initPasswordCheck()
        //3번 패스워드 동일 체크
        initPasswordEqual()


    }

    //중복 확인(코드 수정 필요..textwather에 넣어야 할듯 이것도...)
     fun initButtonCheck(email: String) {
        binding.apply {
            binding.emailDuplicationAfter.setOnClickListener {
                if (email == editTextEmail.text.toString().trim()) {
                    val check = false
                    flagButton = 0
                    viewModel.ButtonLiveFlag(check)
//                    emailDuplicationAfter.isEnabled = false
                    textEmail.setTextColor(
                        ContextCompat.getColor(
                            applicationContext!!,
                            R.color.red
                        )
                    )
                    editTextEmail.setBackgroundResource(R.drawable.btn_emailbox_error)
                    textEmail.text = "중복된 이메일입니다. 다른 이메일을 사용해주세요."
                } else {
                    flagButton = 1
                    val check = true
                    viewModel.ButtonLiveFlag(check)
//                    emailDuplicationAfter.isEnabled = true
                    textEmail.setTextColor(
                        ContextCompat.getColor(
                            applicationContext!!,
                            R.color.main_green
                        )
                    )
                    textEmail.text = "사용 가능한 이메일 입니다."
                }
//                emailDuplicationAfter.isEnabled = true
            }
        }

    }

    //1. 이메일
    fun inintEmailCheck() {

        //textwathcher
        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
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
                checkEmail()

                val flagCheck = viewModel.getFlag()
                Log.d("flagcheck",flagCheck.toString())
                Log.d("flagcheck2",flagButton.toString())
                //다음화면 넘어가기(사용자가 비밀번호까지 입력하고 변심하여 이메일을 바꿀수도 있기에 체크해야함)
                if ((flagEmail == 1) && (flagEqual == 1) && (flagPassword == 1) && flagCheck) {
//                if (checkEmail() && checkPassword() && checkPasswordEqual()) {
                    Log.d("flagEmail", flagEmail.toString())
                    Log.d("flagPassword", flagPassword.toString())
                    Log.d("flagEqual", flagEqual.toString())
                    binding.btnContinueAfter.isEnabled = true

                    initNext()

                } else {
                    binding.btnContinueAfter.isEnabled = false

                }
            }
        })
    }

    //2. 패스워드
    fun initPasswordCheck() {

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
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
                checkPassword()

                Log.d("viewmodel2", flagPassword.toString())
                //현재까지 입력된 패스워드 저장
                passwordInput = binding.editTextPassword.text.toString()


                //다음화면 넘어가기(사용자가 비밀번호까지 입력하고 변심 바꿀수도 있기에 체크해야함)

                val flagCheck = viewModel.getFlag()
                //다음화면 넘어가기(사용자가 비밀번호까지 입력하고 변심하여 이메일을 바꿀수도 있기에 체크해야함)
                if ((flagEmail == 1) && (flagEqual == 1) && (flagPassword == 1) && flagCheck) {
                    binding.btnContinueAfter.isEnabled = true
                    Log.d("flagEmail", flagEmail.toString())
                    Log.d("flagPassword", flagPassword.toString())
                    Log.d("flagEqual", flagEqual.toString())

                    initNext()

                } else {
                    binding.btnContinueAfter.isEnabled = false

                }
            }
        })

    }

    //3. 패스워드 동일
    fun initPasswordEqual() {

        binding.apply {

            editTextPasswordAgain.addTextChangedListener(object : TextWatcher {
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

                // text가 바뀔 때마다 호출된다.
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //비밀번화 일치 true false로 반환
                    checkPasswordEqual()


                    val flagCheck = viewModel.getFlag()
                    Log.d("check4",flagCheck.toString())
                    //다음화면 넘어가기(사용자가 비밀번호까지 입력하고 변심하여 이메일을 바꿀수도 있기에 체크해야함)
                    if ((flagEmail == 1) && (flagEqual == 1) && (flagPassword == 1) && flagCheck) {
                        binding.btnContinueAfter.isEnabled = true
                        Log.d("flagEmail", flagEmail.toString())
                        Log.d("flagPassword", flagPassword.toString())
                        Log.d("flagEqual", flagEqual.toString())

                        initNext()

                    } else {
                        binding.btnContinueAfter.isEnabled = false

                    }
                }
            })
        }
    }


    //1번 이메일 형식 체크
    fun checkEmail(): Boolean {
        binding.apply {

            var email = editTextEmail.text.toString().trim()
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            if (pattern.matcher(email).matches()) {

                editTextEmail.setBackgroundResource(R.drawable.btn_emailbox)
                textEmail.text = "사용 가능한 이메일 형식 입니다."
                textEmail.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )


                return true
            } else {
                editTextEmail.setBackgroundResource(R.drawable.btn_emailbox_error)
                textEmail.text = "맞는 이메일 형식을 입력하세요"
                textEmail.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                //또는 questionEmail.setTextColor(R.color.red.toInt())


                flagEmail = 0
                return false
            }
        }
    }

    //2번 비밀번호 체크
    fun checkPassword(): Boolean {
        binding.apply {

            var password = editTextPassword.text.toString().trim() //공백제거
            val pattern =
                Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$")

            val macher = pattern.matcher(password)
            if (macher.matches()) {

                editTextPassword.setBackgroundResource(R.drawable.btn_custom)
                textPassword.text = "사용 가능한 비밀번호 입니다."
                textPassword.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )

                flagPassword = 1
                return true
            } else {
                editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                textPassword.text = "특수문자 포함 8자 이상 입력해야 합니다."
                textPassword.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                //또는 questionEmail.setTextColor(R.color.red.toInt())
                flagPassword = 0

                return false
            }
        }
    }

    //3번 패스워드 동일 체크
    fun checkPasswordEqual(): Boolean {
        var passwordEqual = passwordInput.trim()
        binding.apply {

            //비밀번화 워쳐와 현재 eiditext가 일치하는 경우
            if (passwordEqual == editTextPasswordAgain.text.toString()) {

                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom)
                textPasswordAgain.text = "비밀번호와 일치합니다."
                textPasswordAgain.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.main_green
                    )
                )

                flagEqual = 1
                return true
            } else {
                editTextPasswordAgain.setBackgroundResource(R.drawable.btn_custom_red)
                textPasswordAgain.text = "비밀번호와 일치하지 않습니다. 다시 확인해주세요."
                textPasswordAgain.setTextColor(
                    ContextCompat.getColor(
                        applicationContext!!,
                        R.color.red
                    )
                )
                flagEqual = 0
                return false
            }
        }
    }

    private fun initNext() {
        binding.apply {

            btnContinueAfter.setOnClickListener {

                val intent = Intent(this@Signin4Activity, SigninDescriptionActivity::class.java)
                startActivity(intent)

            }
        }
    }


}