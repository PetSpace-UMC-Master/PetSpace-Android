package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.Home2Response
import com.example.petsapce_week1.vo.HomeResponse
import com.kakao.sdk.user.model.User
import retrofit2.Call
import retrofit2.http.*

interface homeAPI {

    @GET("/app/rooms/")
    fun getPage(
        @Query("page") page: Int
    ): Call<HomeResponse>

    @GET("/app/rooms/")
    fun getSort(
        @Query("sortBy") sortBy: String
    ): Call<HomeResponse>

    @GET("/app/rooms/")
    fun getCategory(
        @Query("categoryType") categoryType: String
    ): Call<HomeResponse>

    @GET("/app/rooms/")
    fun getDouble(
        @Query("sortBy") sortBy: String,
        @Query("categoryType") categoryType: String
    ): Call<HomeResponse>

    @GET("/app/rooms/")
    fun getTriple(
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String,
        @Query("categoryType") categoryType: String
    ): Call<HomeResponse>

//    cur 'http://localhost:8080/app/rooms/filtering?startDay=2022-01-01&endDay=2022-01-02'

    @GET("app/rooms/filtering/")
    fun getAll(
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String,
        @Query("categoryType") categoryType: String,
        @Query("startDay") startDay: String,
        @Query("endDay") endDay: String,
        @Query("keyword") keyword: String,
        @Query("people") people: Int,
        @Query("pets") pets: Int
    ):Call<Home2Response>
}