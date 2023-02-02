package com.example.petsapce_week1.loginrelated

import android.app.ProgressDialog.show
import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.*
import com.example.petsapce_week1.databinding.ActivityLoginBinding
import com.example.petsapce_week1.home.HomeActivity
import com.example.petsapce_week1.network.LoginService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.reviewrelated.ReviewReadMoreActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance() // RetrofitClient의 instance 불러오기
    private var authToken : String ?= null
    val bearer = "Bearer "
    var token: String ?= null
    var refreshToken_received : String ?= null
    var api : LoginService = retrofit.create(LoginService::class.java)

    //토큰 재발급 시
    var apiReissue :LoginService = retrofit.create(LoginService::class.java)
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //id password 임의로 설정
        val id = "tkdwls@naver.com"
        val password = "1234567!"

        //id, password check
        initFlag(id, password)

        //회원가입 화면으로 넘어감(채윤 화면)
        initNext()

        // == kakao login ==
        retrofit = Retrofit.Builder()
            .baseUrl("https://99f0-211-106-114-186.jp.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 지우지 말것!!!!
        //로그인 정보 확인
        UserApiClient.instance.accessTokenInfo{ tokenInfo, error ->
            if(error != null){
                //Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if(tokenInfo != null) {
                //Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnKakao.setOnClickListener {
            kakaoLogin()
        }

    }
    // ================ 카카오 로그인 ==================
    private fun kakaoLogin() {

        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
//                when {
//                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
//                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
//                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
//                    }
//                    else -> { // Unknown
//                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
//                    }
//                }
            } else if (token != null) {
                Log.i(ContentValues.TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                authToken = token.accessToken
                Log.d("access_token", "${authToken}authToken")
                //UserModel(accessToken = authToken.toString())
                //data = UserModel(accessToken = authToken)
                //saveData(id, pw)
                Log.d("access_token2", "$authToken")

                //bearer 붙이나??
                //authToken = bearer + authToken
                Log.d("로그인 내가 보낸거", token.toString())

                api.postAccessToken(UserModelKakao(accessToken = authToken)).enqueue(object : Callback<LoginBackendResponse>{
                    override fun onResponse(call: Call<LoginBackendResponse>, response: Response<LoginBackendResponse>) {
                        Log.d("로그인 통신 성공", response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())

                        when (response.code()) {
                            200 -> {
                                Log.d("로그인 성공" , "ggg")
                            }
//                            405 -> Toast.makeText(
//                                this@LoginActivity,
//                                "로그인 실패 : 아이디나 비번이 올바르지 않습니다",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            500 -> Toast.makeText(
//                                this@LoginActivity,
//                                "로그인 실패 : 서버 오류",
//                                Toast.LENGTH_LONG
//                            ).show()
                        }
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                        Log.d("통신 로그인..", "전송 실패")
                    }
                })
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity) { token, error ->

                if (error != null) {
                    Log.e(ContentValues.TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
                } else if (token != null) {
                    Log.i(ContentValues.TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")

                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
        }
    }

    fun initFlag(id: String, password: String) {
        val severId = id
        val severPswd = password


        binding.apply {

            btnEmail.setOnClickListener {

                val inputEmail = editTextEmail.text.toString()
                val inputPassword = editTextPassword.text.toString()

                // ==================== 일반 로그인 백엔드 통신 부분 ===================
                val data = UserModelGeneral(inputEmail, inputPassword)

                if (severId == inputEmail && severPswd == inputPassword) {
                    api.userLogin(data).enqueue(object : Callback<LoginBackendResponse> {
                        override fun onResponse(
                            call: Call<LoginBackendResponse>,
                            response: Response<LoginBackendResponse>
                        ) {
                            Log.d("로그인 통신 성공",response.toString())
                            Log.d("로그인 HTTP 코드", response.code().toString())
                            Log.d("로그인 통신 성공", response.body().toString())

                            when (response.code()) {
                                200 -> {
                                    // == 기기 db (shared preference) 로 저장
                                    saveData(inputEmail, inputPassword)
                                }
                                //400 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                //500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                            // 실패
                            Log.d("로그인 통신 실패",t.message.toString())
                            Log.d("로그인 통신 실패","fail")
                        }
                    })
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)

                }//틀리면 빨간글자 뜨게함
                else {
                    editTextEmail.setBackgroundResource(R.drawable.btn_custom_red)
                    editTextPassword.setBackgroundResource(R.drawable.btn_custom_red)
                    textViewWarningRed.visibility = View.VISIBLE

                    /*editTextEmail.visibility = View.GONE
                    editTextPassword.visibility = View.GONE
                    editTextEmailRed.visibility = View.VISIBLE
                    editTextPasswordRed2.visibility = View.VISIBLE*/

                    api.userLogin(data).enqueue(object : Callback<LoginBackendResponse> {
                        override fun onResponse(
                            call: Call<LoginBackendResponse>,
                            response: Response<LoginBackendResponse>
                        ) {
                            Log.d("로그인 통신 성공",response.toString())
                            Log.d("로그인 HTTP 코드", response.code().toString())
                            Log.d("로그인 통신 성공", response.body().toString())

                            when (response.code()) {
                                200 -> {
                                    // == 기기 db (shared preference) 로 저장
                                    saveData(inputEmail, inputPassword)
                                }
                                //400 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                //500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                            // 실패
                            Log.d("로그인 통신 실패",t.message.toString())
                            Log.d("로그인 통신 실패","fail")
                        }
                    })
                }
            }
        }
    }

    private fun initNext() {
        binding.apply {
            btnNewAccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, Signin4Activity::class.java)
                startActivity(intent)
            }
        }
    }

    fun saveData( id : String, pw : String){
        val prefID = getSharedPreferences("userID", MODE_PRIVATE)
        val prefPW = getSharedPreferences("userPW", MODE_PRIVATE)
        val editID = prefID.edit()
        val editPW = prefPW.edit()
        editID.putString("id", id)
        editPW.putString("pw", pw)
        editID.apply()//save
        editPW.apply()//save
        Log.d("로그인 데이터", "saved")
    }
}