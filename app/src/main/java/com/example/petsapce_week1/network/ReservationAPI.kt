package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.ReservationCreateResponse
import com.example.petsapce_week1.vo.ReservationReadResponse
import com.example.petsapce_week1.vo.ReservationUserData
import retrofit2.Call
import retrofit2.http.*

interface ReservationAPI {

    @POST("/app/reservations/")
    fun postReservation(
        @Header("Authorization") accessToken : String,
        @Body jsonParams : ReservationUserData,
        @Query("roomId") roomId : Long
    ): Call<ReservationCreateResponse>

    @GET("/app/reservations/terminate")
    fun getReservationReadTerminate(
        @Header("Authorization") accessToken : String,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): Call<ReservationReadResponse>

    @GET("/app/reservations/")
    fun getReservationReadUpcoming(
        @Header("Authorization") accessToken : String,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): Call<ReservationReadResponse>
}