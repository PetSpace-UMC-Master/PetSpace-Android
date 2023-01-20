package com.example.petsapce_week1.loginrelated

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

//서버 주소로부터 데이터 GET, POST, ...
/*
@Get("policy"): baseUrl 뒤에 /policy가 붙는다.
@Query("location"): 주소에 들어가는 파라미터인 location을 @Query로 지정한다.
@Header("Authorization"): 서버가 토큰 인증을 사용중이고 헤더에 토큰을 담아 넘겨줘야 하는 경우 @Header("Authorization")을 추가한다.
Call: 서버로부터 데이터를 PolicyResponse 타입으로 받아온다.
 */
interface LoginService {
    //Kakao Login
    @POST("/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String,
        @Body jsonParams : UserModelKakao
    ): Call<LoginBackendResponse>

    //General Login
    @POST("/app/login")
    fun userLogin(
        @Body jsonParams : UserModelGeneral
    ): Call<LoginBackendResponse>

    //2 -> idx
    @POST("/app/users/2")
    fun PostUserToken(
        @Header("Authorization") accessToken : UserToken,
    ):Call<LoginBackendResponse>

    @GET("/app/users/2")
    fun GetUserInfo(
        @Header("Authorization") accessToken: String,
    ):Call<UserDetailResponse>

    /*
    companion object {
        private val gson = GsonBuilder().setLenient().create()
        fun loginRetrofit(): LoginService {
            return Retrofit.Builder()
                .baseUrl("https://8e27-211-106-114-186.jp.ngrok.io/")
                // .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LoginService::class.java)
        }
    }

     */
}