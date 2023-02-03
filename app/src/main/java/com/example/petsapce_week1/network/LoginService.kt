package com.example.petsapce_week1.network

import com.example.petsapce_week1.loginrelated.*

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    //Kakao Login
    @POST("/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String
        @Body jsonParams: UserModelKakao// => token
    ): Call<LoginBackendResponse>

    //General Login
    @POST("/app/login")
    fun userLogin(
        @Body jsonParams: UserModelGeneral,
    ): Call<LoginBackendResponse>

    @GET("/app/users/2")
    fun GetUserInfo(
        @Header("Authorization") accessToken: String,
    ):Call<UserDetailResponse>

    @POST("/app/token-reissue")
    fun TokenReissue(
        @Body jsonParams: ReissueData
    ):Call<LoginBackendResponse>

}
