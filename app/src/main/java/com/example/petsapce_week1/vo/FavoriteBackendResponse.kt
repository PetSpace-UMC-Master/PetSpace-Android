package com.example.petsapce_week1.vo

data class FavoriteBackendResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result3
)

data class Favorite(
    val availableDays: List<Any>,
    val averageReviewScore: Int,
    val id: Int,
    val numberOfReview: Int,
    val price: Int,
    val roomAddress: String,
    val roomImages: List<String>
)

data class Result3(
    val favorites: List<Favorite>,
    val isLast: Boolean,
    val page: Int
)

