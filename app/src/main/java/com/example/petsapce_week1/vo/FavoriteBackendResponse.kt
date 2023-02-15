package com.example.petsapce_week1.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavoriteBackendResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val favorites: List<Favorite>,
        val isLast: Boolean,
        val page: Int
    ){
        data class Favorite(
            @SerializedName("averageReviewScore")
            val averageReviewScore: Double,
            @SerializedName("id")
            val id: Int,
            @SerializedName("numberOfReview")
            val numberOfReview: Int,
            @SerializedName("price")
            val price: Int,
            @SerializedName("roomAddress")
            val roomAddress: String,
            @SerializedName("roomImages")
            val roomImages: List<String>
        ): Serializable
    }

}

data class FavoriteData(
    @SerializedName("averageReviewScore")
    val averageReviewScore: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("numberOfReview")
    val numberOfReview: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("roomAddress")
    val roomAddress: String,
    @SerializedName("roomImages")
    val roomImages: List<String>
): Serializable

