package com.example.petsapce_week1.vo

//예약 시 받는 데이터
data class ReservationReadResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val isLast: Boolean,
        val page: Int,
        val reservations: List<ReservationReadResponse.Reservation>
    )

    data class Reservation(
        val endDate: String,
        val remainingDays: Int,
        val reservationCode: String,
        val reviewCreated: Boolean,//true => 수정, false => 작성
        val roomId: Long,
        val roomImageUrls: List<String>,
        val roomName: String,
        val startDate: String
    )

}