package com.example.petsapce_week1.vo

//예약 시 보내는 데이터
data class ReservationUserData(
    val totalGuest : Int,
    val totalPet : Int,
    val startDate : String,
    val endDate : String
)
