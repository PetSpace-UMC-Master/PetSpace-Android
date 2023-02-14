package com.example.petsapce_week1.vo.accomo_datamodel


//백엔드로부터 받는 데이터 클래스
data class AccomodationData(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val address: String,
        val checkinTime: String,
        val checkoutTime: String,
        val facilities: List<Facility>,
        val hostName: String,
        val hostId : Int,
        val latitude : String,
        val longitude: String,
        val maxGuest: Int,
        val maxPet: Int,
        val price: Int,
        val reviewCount: Int,
        val reviewPreviews: List<ReviewPreview>,
        val roomAverageScore: Double,
        val roomDecription: String,
        val roomId: Int,
        val roomImageUrls: List<String>,
        val roomName: String,
        val favorite : Boolean
    )

    data class Facility(
        val facilityImageUrl: String,
        val facilityName: String
    )

    data class ReviewPreview(
        val createdAt: String,
        val description: String,
        val nickname: String,
        val userId: Int,
        val score : Int,
        val profileImage: String
    )
}
