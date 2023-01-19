package com.example.petsapce_week1.reviewrelated

//리뷰 화면 내 리사이클러뷰 review_item_list 데이터 클래스
data class ReviewItem(
    val time: String,
    val nickName : String,
    val content : String,
    val img : Int,
)
