package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.ReviewGetData
import retrofit2.Call
import retrofit2.http.*

interface ReviewGETAPI {

    @GET("/app/reviews/")
    fun getPageofReview(
        @Query("page") page: Int
    ): Call<ReviewGetData>

    @GET("/app/reviews/")
    fun getSizeofReview(
        @Query("size") size: Int
    ): Call<ReviewGetData>

}