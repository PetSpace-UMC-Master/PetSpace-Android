package com.example.petsapce_week1.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitActivity:Application() {
    companion object{
        var networkService:NetworkService
        //baseUrl 선언후 get으로 받게 되면 "https://baserurl/get으로 받은부분" 이런식으로 가게됨

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        init { //서비스 객체 얻기
            networkService = retrofit.create(NetworkService::class.java)
        }
        val userListCall = networkService.getList("1")



    }
}