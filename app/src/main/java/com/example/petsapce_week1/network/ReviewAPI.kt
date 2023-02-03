package com.example.petsapce_week1.network

import com.example.petsapce_week1.review.ReviewData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ReviewAPI {
   @Multipart
    @POST("/app/reviews?reservationId=1/")
    fun post_reviews(
        //@Header("")
        //@Part jsonParams: ReviewDTO,
       @PartMap data: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part?
        //@Body jsonParams: ReviewDTO
    ): Call<ReviewData>

  //  abstract fun post_reviews(jsonParams: ReviewDTO): Call<ReviewData>
}