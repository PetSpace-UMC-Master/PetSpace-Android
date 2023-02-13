package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.ReviewGetData
import retrofit2.Call
import retrofit2.http.*

interface ReviewGETAPI {
    @GET("/app/reviews?roomId=1&?page=1&size=5")
    fun getReviews(
        @Query("roomId") roomId: Long,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ReviewGetData>
}