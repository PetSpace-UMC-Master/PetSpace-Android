package com.example.petsapce_week1.vo

data class ReviewData(
    val createdAt: String,
    val description: String,
    val nickname: String,
    val userId: Int,
    val score : Int
)
