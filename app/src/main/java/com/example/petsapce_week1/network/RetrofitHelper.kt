package com.example.petsapce_week1.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체 생성

object RetrofitHelper {

    val BASE_URL: String = "https://b0e9-121-167-200-122.jp.ngrok.io"


    var gson = GsonBuilder().setLenient().create()

    fun getRetrofitInstance(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}