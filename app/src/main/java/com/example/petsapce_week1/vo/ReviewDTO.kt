package com.example.petsapce_week1.vo.accomo_datamodel

//백엔드로 주는 데이터 클래스
data class ReviewDTO(
    val content: String? = null,
    val score: Int? = null,
    val fileToUpload: Any?
)
