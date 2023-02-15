package com.example.petsapce_week1.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체 생성

object RetrofitHelper_2 {


    val BASE_URL: String = "https://08e6-125-143-134-113.jp.ngrok.io"

    var gson = GsonBuilder().setLenient().create()

    val httpClient = OkHttpClient.Builder()

    val baseBuilder = Retrofit.Builder()
        .baseUrl((BASE_URL))
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createBaseService(serviceClass: Class<S>?): S {
        val retrofit = baseBuilder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    fun getRetrofitInstance(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(RetrofitHelperHome.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(RetrofitHelperHome.gson))
            .build()

        return retrofit
    }
}