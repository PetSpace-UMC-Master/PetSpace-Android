package com.example.petsapce_week1.network

import com.example.petsapce_week1.loginrelated.LoginBackendResponse
import com.example.petsapce_week1.loginrelated.UserModelGeneral
import com.example.petsapce_week1.loginrelated.UserModelKakao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    //Kakao Login
    @POST("/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String
        @Body jsonParams : UserModelKakao

    ): Call<LoginBackendResponse>

    //General Login
    @POST("/app/login")
    fun userLogin(
        @Body jsonParams : UserModelGeneral,
    ): Call<LoginBackendResponse>
}