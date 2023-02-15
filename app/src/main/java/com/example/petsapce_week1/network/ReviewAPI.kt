package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.ReviewPostData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ReviewAPI {
   @Multipart
    @POST("/app/reviews?reservationId=1")
    fun post_reviews(
       @Header("Authorization") accessToken: String,
        //@Part jsonParams: ReviewDTO,
       @PartMap data: HashMap<String, RequestBody>,
       @Part reviewImages: List<MultipartBody.Part>?
    ): Call<ReviewPostData>

  //  abstract fun post_reviews(jsonParams: ReviewDTO): Call<ReviewData>
}