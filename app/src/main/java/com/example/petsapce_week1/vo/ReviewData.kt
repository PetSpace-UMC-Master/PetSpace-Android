package com.example.petsapce_week1.vo

import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData

data class ReviewData(
    val createdAt: String,
    val description: String,
    val nickname: String,
    val userId: Int,
    val score : Int
)

// 하나의 시설에 대한 정보
data class FacilityData(
    val imgUrl : String,
    val facname : String,
)

// category 하나와 이 카테고리의 여러 시설들
data class FacilityReceived(
    var category: String,
    var facilities: List<AccomodationData.Facility>
)