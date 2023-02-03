package com.example.petsapce_week1.vo

data class ReviewPostData(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: ReviewPostResult
)

data class ReviewPostResult(
    val content: String,
    val id: Int,
    val score: Int
)