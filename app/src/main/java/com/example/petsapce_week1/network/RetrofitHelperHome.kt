package com.example.petsapce_week1.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체 생성
object RetrofitHelperHome {

    //base_url 애들 마다 다 다름 얘들아 테섭 하나 만들어죠
    val BASE_URL: String = "https://62f3-211-106-114-186.jp.ngrok.io"
    var gson = GsonBuilder().setLenient().create()

    fun getRetrofitInstance(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}