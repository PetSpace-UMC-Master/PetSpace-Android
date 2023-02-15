package com.example.petsapce_week1.vo

data class ReservationCreateResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val reservationId: Int
    )
}