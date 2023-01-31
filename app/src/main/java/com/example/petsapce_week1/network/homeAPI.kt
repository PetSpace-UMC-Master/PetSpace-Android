package com.example.petsapce_week1.network

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

}