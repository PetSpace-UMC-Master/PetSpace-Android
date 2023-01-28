package com.example.petsapce_week1.review

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReviewAPI {
    @POST("/app/reviews?reservationId=1")
    fun post_reviews(
        @Body jsonParams: ReviewDTO
    ): Call<ReviewData>
}