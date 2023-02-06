package com.example.petsapce_week1.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    val BASE_URL: String = "https://a2e4-115-94-178-52.jp.ngrok.io"

    var gson = GsonBuilder().setLenient().create()

    fun getRetrofitInstance(): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(BASE_URL)
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}