package com.example.petsapce_week1.review

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReviewAPI {

    @POST("/reviews")
    @Headers("accept: application/json",
    "content-type:application/json")
    fun post_reviews(
        @Body jsonparams: ReviewDTO
    ): Call<PostResult>

    @GET("/reviews")
    @Headers("accept: application/json",
    "content-type: application/json")
    fun get_reviews(
    ): Call<HTTP_GET_MODEL>

    companion object {
        private const val BASE_URL = "127.0.0.1:8088"

        fun create(): APIS {
            val gson: Gson = GsonBuilder().setLen
        }
    }
}