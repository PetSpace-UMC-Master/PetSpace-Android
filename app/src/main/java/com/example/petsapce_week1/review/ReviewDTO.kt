package com.example.petsapce_week1.review

import com.google.gson.annotations.SerializedName

//JSON data를 받아올 데이터 클래스

data class ReviewDTO(
    @SerializedName("score") private int score;
    @SerializedName("content") private String content;
    val id : String ?= null,

)
