package com.example.petsapce_week1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.petsapce_week1.databinding.ActivitySettingsBinding
import com.example.petsapce_week1.home.HomeActivity
import com.example.petsapce_week1.home.homefragment.ProfileFragment
import com.example.petsapce_week1.loginrelated.LogoutBackendResponse
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.LoginService
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.vo.ReservationReadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SettingsActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingsBinding

    var accessToken : String ?= null
    var accommoList = ArrayList<ReservationReadResponse.Reservation>()

    private fun getAccessToken() {
        val atpref = getSharedPreferences("accessToken", Context.MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        accessToken = "Bearer $accessToken"
    }


    // ========== 백엔드 연동 부분 ===========
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    // 기본 숙소 정보 불러올때 호출
    var api : LoginService = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSettingsBack.setOnClickListener {
            val intent = Intent(this, ProfileMenuFragment::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            getAccessToken()
            accessToken?.let { it1 ->
                api.userLogout(accessToken = it1).enqueue(object :
                    Callback<LogoutBackendResponse> {
                    override fun onResponse(
                        call: Call<LogoutBackendResponse>,
                        response: Response<LogoutBackendResponse>
                    ) {
                        Log.d("로그아웃", response.toString())
                        Log.d("로그아웃", response.body().toString())
                        val prefAccessToken : SharedPreferences = getSharedPreferences("accessToken", MODE_PRIVATE)
                        val prefRefreshToken : SharedPreferences = getSharedPreferences("refreshToken", MODE_PRIVATE)
                        val editAT : SharedPreferences.Editor  = prefAccessToken.edit()
                        val editRT : SharedPreferences.Editor = prefRefreshToken.edit()
                        editAT.clear()
                        editRT.clear()
                        editAT.commit()
                        editRT.commit()

                        getAccessToken()
                        Log.d("로그아웃 그 이후..", accessToken.toString())
                        Toast.makeText(this@SettingsActivity, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()

                        val intent2 = Intent(this@SettingsActivity, HomeActivity::class.java)
                        startActivity(intent2)
                    }

                    override fun onFailure(call: Call<LogoutBackendResponse>, t: Throwable) {
                        Log.d("로그아웃 실패", t.toString())
                    }

                })
            }
        }

    }
}