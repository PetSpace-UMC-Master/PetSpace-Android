package com.example.petsapce_week1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.petsapce_week1.databinding.ActivitySigninDescriptionBinding

class SigninDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNext()
        initPrevious()
/*        checkName()
        checkNickname()
        checkBirth()*/

        binding.editTextName.addTextChangedListener(nameListener)
        binding.editTextNickname.addTextChangedListener(nicknameListener)
        binding.editTextBirth.addTextChangedListener(birthListener)



    }

    var nameFlag = false
    var nicknameFlag = false
    var birthFlag = false


    private fun initNext() {
        binding.apply {
            btnContinue.setOnClickListener {
                saveUserData(editTextNickname.toString(), editTextName.toString(), editTextBirth.toString())

                val intent = Intent(this@SigninDescriptionActivity,TermsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initPrevious() {
        binding.apply {
            btnBack.setOnClickListener {
                val intent = Intent(this@SigninDescriptionActivity, Signin4Activity::class.java)
                startActivity(intent)
            }
        }
    }


/*    fun checkName(): Boolean {
        binding.apply {
            var name = editTextName.text.toString().trim()
            if ((name.length < 2) or (name.length > 8)) {
                editTextName.setBackgroundResource(R.drawable.btn_custom_red)
                textName.text = "2자 이상 8자 이하의 한글로 입력해주세요"
                textName.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                return false
            }
            else {
                editTextName.setBackgroundResource(R.drawable.btn_custom)
                textName.text = ""
                return true
            }
        }
    }*/

    // 이름 조건 체크 (2자 이상 8자 이하 한글)
    fun nameRegex(name: String): Boolean {
        return name.matches("^[ㄱ-ㅎ가-힣]{2,8}$".toRegex())
    }

    // 닉네임 조건 체크 (2자 이상 16자 이하 한글)
    fun nicknameRegex(nickname: String): Boolean {
        return nickname.matches("^[ㄱ-ㅎ가-힣]{2,16}$".toRegex())
    }

    // 생일 조건 체크 (8자 숫자)
    fun birthRegex(birth: String): Boolean {
        return birth.matches("^[0-9]{8}$".toRegex())
    }

    private val nameListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() || !nameRegex(s.toString()) -> {
                        binding.apply {
                            editTextName.setBackgroundResource(R.drawable.btn_custom_red)
                            textName.text = "2자 이상 8자 이하의 한글로 입력해주세요"
                            textName.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                        }
                        nameFlag = false
                    }

                    else -> {
                        binding.apply {
                            editTextName.setBackgroundResource(R.drawable.btn_custom)
                            textName.text = ""
                        }
                        nameFlag = true
                        Log.d("여22기", "ㄴㄴ")
                    }
                }
            }
        }
    }

    private val nicknameListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() || !nicknameRegex(s.toString()) -> {
                        binding.apply {
                            editTextNickname.setBackgroundResource(R.drawable.btn_custom_red)
                            textNickname.text = "2자 이상 16자 이하의 한글로 입력해주세요"
                            textNickname.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                        }
                        nicknameFlag = false
                    }

                    else -> {
                        binding.apply {
                            editTextNickname.setBackgroundResource(R.drawable.btn_custom)
                            textNickname.text = ""
                        }
                        nicknameFlag = true
                        Log.d("여기33", "ㄴㄴ")
                    }
                }
            }
        }
    }


    private val birthListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() || !birthRegex(s.toString()) -> {
                        binding.apply {
                            editTextBirth.setBackgroundResource(R.drawable.btn_custom_red)
                            textBirth.text = "숫자 8자리로 입력해주세요"
                            textBirth.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                        }
                        birthFlag = false
                    }

                    else -> {
                        binding.apply {
                            editTextBirth.setBackgroundResource(R.drawable.btn_custom)
                            textBirth.text = ""
                        }
                        birthFlag = true
                        Log.d("여기44", "ㄴㄴ")
                    }
                }
            }
        }
    }
    @SuppressLint("CommitPrefEdits")
    fun saveUserData(nickname: String, name: String, birth: String){
        val prefNickName  : SharedPreferences = getSharedPreferences("userNickname", MODE_PRIVATE)
        val prefName  : SharedPreferences= getSharedPreferences("userName", MODE_PRIVATE)
        val prefBirth  : SharedPreferences= getSharedPreferences("userBirth", MODE_PRIVATE)
        val editNickname  : SharedPreferences.Editor = prefNickName.edit()
        val editName  : SharedPreferences.Editor = prefName.edit()
        val editBirth  : SharedPreferences.Editor = prefBirth.edit()
        editNickname.putString("nickname", nickname).apply()
        editName.putString("name", name).apply()
        editBirth.putString("birth", birth).apply()

        Log.d("user 데이터", "saved")
    }

/*    fun checkNickname(): Boolean {
        binding.apply {
            var Nickname = editTextNickname.text.toString().trim()
            if ((Nickname.length < 2) or (Nickname.length > 16)) {
                editTextNickname.setBackgroundResource(R.drawable.btn_custom_red)
                textNickname.text = "2자 이상 16자 이하의 한글로 입력해주세요"
                textNickname.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                return false
            }
            else {
                editTextNickname.setBackgroundResource(R.drawable.btn_custom)
                textNickname.text = ""
                return true
            }
        }
    }

    fun checkBirth(): Boolean {
        binding.apply {
            var birth = editTextBirth.text.toString().trim()
            if (birth.length != 8) {
                editTextBirth.setBackgroundResource(R.drawable.btn_custom_red)
                textBirth.text = "숫자 8자리로 입력해주세요"
                textBirth.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.red))
                return false
            }
            else {
                editTextBirth.setBackgroundResource(R.drawable.btn_custom)
                textBirth.text = ""
                return true
            }
        }
    }*/
}