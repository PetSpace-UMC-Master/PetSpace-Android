package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.petsapce_week1.databinding.ActivityTermsBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.network.LoginService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.SignupData
import com.example.petsapce_week1.vo.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class TermsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsBinding
    var if1Checked: Int = 0
    var if2Checked: Int = 0

    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : LoginService = retrofit.create(LoginService::class.java)

    var birth: String ?= null
    var checkedPassword: String ?= null
    var email: String ?= null
    var marketingAgreement: Boolean = false
    var nickname: String ?= null
    var password: String ?= null
    var username: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //더보기 text 버튼 클릭 이벤트 리스너 (고정)
        binding.btnViewMore1.setOnClickListener {
            val intent = Intent(this, TermsContentActivity1::class.java)
            startActivity(intent)
        }

        //더보기 text 버튼 클릭 이벤트 리스너 (고정)
        binding.btnViewMore2.setOnClickListener {
            val intent = Intent(this, TermsContentActivity2::class.java)
            startActivity(intent)
        }

          //checkbox 모두 선택 시 버튼 활성화
          var if1Checked: Int = 0
          var if2Checked: Int = 0
          binding.checkboxAgreeToInfo.setOnCheckedChangeListener { buttonView, isChecked ->
              if (isChecked) {
                  if1Checked = 1
                  if (if2Checked == 1) {
                      binding.btnComleteAfter.visibility = View.VISIBLE
                  }
              } else {
                  if1Checked = 0
                  binding.btnComleteAfter.visibility = View.INVISIBLE
              }
          } //checkbox 버튼
          binding.checkboxAgreeToMarketing.setOnCheckedChangeListener { buttonView, isChecked ->
              if (isChecked) {
                  if2Checked = 1
                  if (if1Checked == 1) {
                      binding.btnComleteAfter.visibility = View.VISIBLE
                  } else {
                      binding.btnComleteAfter.visibility = View.INVISIBLE
                  }
              } else {
                  binding.btnComleteAfter.visibility = View.INVISIBLE
              }
          }

        //var accessToken : String ?= null
        //        val atpref = getSharedPreferences("accessToken", MODE_PRIVATE)
        //        if(atpref != null){
        //            accessToken = atpref.getString("accessToken", "default")
        //        }

        getSharedData()
        Log.d("db 가져옴","${birth}, ${username}")


        binding.btnComleteAfter.setOnClickListener {

            birth?.let { it1 -> email?.let { it2 -> nickname?.let { it3 -> password?.let { it4 -> username?.let { it5 -> SignupData(birth = it1, email = it2, nickname = it3, checkedPassword = it4, password = password!!, marketingAgreement = true, username = it5) } } } } }
                ?.let { it2 ->
                    api.SignUpPost(it2).enqueue(object : Callback<SignupResponse>{
                        override fun onResponse(
                            call: Call<SignupResponse>,
                            response: Response<SignupResponse>
                        ) {
                            Log.d("회원 가입 통신 성공",response.toString())
                            Log.d("회원 가입 통신 성공", response.body().toString())

                        }

                        override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                            Log.d("회원 가입 실패", "ㅠㅠ")
                        }

                    })
                }

            val intent = Intent(this, GifActivity::class.java)
            startActivity(intent)
        }


        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
//if(atpref != null){
//            accessToken = atpref.getString("accessToken", "default")
//        }
    fun getSharedData(){

        val birthPref = getSharedPreferences("userBirth", MODE_PRIVATE)
        if(birthPref != null){
            birth = birthPref.getString("birth", "default")
            Log.d("db 생일", "Value of birth: $birth")
        } else {
            Log.d("db birth", "birthPref is null")
        }

        val namePref = getSharedPreferences("userName", MODE_PRIVATE)
        if(namePref != null){
            username = namePref.getString("name", "default")
        }

        val emailPref = getSharedPreferences("userEmail", MODE_PRIVATE)
        if(emailPref != null){
            email = emailPref.getString("email", "default")
        }

        val pwPref = getSharedPreferences("userPw", MODE_PRIVATE)
        if(pwPref != null){
            password = pwPref.getString("pw", "default")
        }

        val nicknamePref = getSharedPreferences("userNickname", MODE_PRIVATE)
        if(nicknamePref != null){
            nickname = nicknamePref.getString("nickname", "default")
        }
        Log.d("회원가입 데이터", "${birth}, ${username}, ${password}, ${nickname}")
    }


/*
    fun ininCheck(): Int {
        //checkbox 모두 선택 시 버튼 활성화

        binding.apply {
            checkboxAgreeToInfo.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if1Checked = 1
//                    btnComleteBefore.setBackgroundResource(R.drawable.btn_customfull)
//                    btnComleteBefore.setBackgroundColor(R.color.main_green)


                } else {
                    if1Checked = 0


                }
            }
            checkboxAgreeToMarketing.setOnCheckedChangeListener { button, isChecked2 ->
                if (isChecked2) {
                    if2Checked = 1


                } else {
                    if2Checked = 0

                }
            }

        }

        return if1Checked
    }
*/
}