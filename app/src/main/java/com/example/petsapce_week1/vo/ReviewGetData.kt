package com.example.petsapce_week1.vo

data class ReviewGetData(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: ReviewGETResult
){
    data class ReviewGETResult(
        val isLast: Boolean,
        val page: Int,
        val reviews: List<Review>
    )

    data class Review(
        val content: String,
        val dayAfterCreated: String,
        val id: Int,
        val nickName: String,
        val profileImage: String,
        val reviewImage: List<String>,
        val score: Int
    )}

