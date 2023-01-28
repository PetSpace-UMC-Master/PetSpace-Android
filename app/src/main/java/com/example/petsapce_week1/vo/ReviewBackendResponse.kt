package com.example.petsapce_week1.review

data class ReviewData(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
)

data class Result(
    val id: Int
)