package com.example.petsapce_week1.network

import com.example.petsapce_week1.loginrelated.*
import com.example.petsapce_week1.vo.EmailCheckResponse
import com.example.petsapce_week1.vo.SignupData
import com.example.petsapce_week1.vo.SignupResponse

import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    //Kakao Login
    @POST("/app/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String
        @Body jsonParams: UserModelKakao// => token
    ): Call<LoginBackendResponse>

    //General Login
    @POST("/app/login")
    fun userLogin(
        @Body jsonParams: UserModelGeneral,
    ): Call<LoginBackendResponse>

    @GET("/app/sign-up/email-check")
    fun EmailCheck(
        @Query("email", encoded = true) email : String,
    ): Call<EmailCheckResponse>

    @POST("/app/sign-up")
    fun SignUpPost(
        @Body jsonParams : SignupData
    ): Call<SignupResponse>

    @POST("/app/logout")
    fun userLogout(
        @Header("authorization") accessToken : String
    ): Call<LogoutBackendResponse>


    @GET("/app/users/2")
    fun GetUserInfo(
        @Header("Authorization") accessToken: String,
    ):Call<UserDetailResponse>

    @POST("/app/token-reissue")
    fun TokenReissue(
        @Body jsonParams: ReissueData
    ):Call<LoginBackendResponse>

}
