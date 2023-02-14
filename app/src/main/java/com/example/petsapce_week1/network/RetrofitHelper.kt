package com.example.petsapce_week1.network

import com.example.petsapce_week1.network.RetrofitHelperHome.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체 생성

object RetrofitHelper {

    //val BASE_URL: String = "https://b93c-115-140-206-21.jp.ngrok.io"
    val BASE_URL: String = "http://3.38.26.120:8080"


    var gson = GsonBuilder().setLenient().create()

    fun getRetrofitInstance(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}