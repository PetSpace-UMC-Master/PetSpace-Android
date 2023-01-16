package com.example.petsapce_week1.loginrelated

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

//서버 주소로부터 데이터 GET, POST, ...
/*
@Get("policy"): baseUrl 뒤에 /policy가 붙는다.
@Query("location"): 주소에 들어가는 파라미터인 location을 @Query로 지정한다.
@Header("Authorization"): 서버가 토큰 인증을 사용중이고 헤더에 토큰을 담아 넘겨줘야 하는 경우 @Header("Authorization")을 추가한다.
Call: 서버로부터 데이터를 PolicyResponse 타입으로 받아온다.
 */
interface LoginService {

/*
    @GET("/test")
    fun getAccessToken(
        @Header("access_token") token : String
    ): Call<Property>

 */
//@Body parameters: HashMap<String, Any>
    //@Body userModel: UserModel
    //@Field("username") email:String,

    @POST("/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String
        @Body jsonParams : UserModel

    ): Call<LoginBackendResponse>

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