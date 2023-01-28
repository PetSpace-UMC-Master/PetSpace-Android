package com.example.petsapce_week1.network

import com.example.petsapce_week1.review.ReviewData
import com.example.petsapce_week1.vo.accomo_datamodel.ReviewDTO
import retrofit2.Call
import retrofit2.http.*

interface ReviewAPI {
   // @Multipart
    @POST("/app/reviews?reservationId=1/")
    fun post_reviews(
        //@Header("")
        //@Part jsonParams: ReviewDTO,
        //@Part images: MultipartBody.Part
        @Body jsonParams: ReviewDTO
    ): Call<ReviewData>
}