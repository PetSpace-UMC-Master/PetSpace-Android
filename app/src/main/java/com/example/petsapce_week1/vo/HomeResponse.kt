package com.example.petsapce_week1.vo


data class HomeResponse(
    val isSuccess: Boolean?,
    val responseCode: Int?,
    val responseMessage: String?,
    val result: Result
)

data class Result(
    val availableDays: List<String?>?,
    val averageReviewScore: Int?,
    val city: String?,
    val district: String?,
    val numberOfReview: Int?,
    val price: Int?,
    val roomId: Int?,
    val roomImages: List<Any?>?
)