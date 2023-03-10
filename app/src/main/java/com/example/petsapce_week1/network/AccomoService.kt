package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.AccommodationFacilityMore
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.HomeResponse
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData

import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import okhttp3.FormBody

import retrofit2.Call
import retrofit2.http.*

interface AccomoService {
    @GET("/app/rooms/{roomId}")
    fun getRoomDetail(
        @Header("Authorization") accessToken: String,
        @Path("roomId") roomId : Long
        //@Body jsonParams : AccomodationRoomData
    ):Call<AccomodationData>

    //숙소 말고 편의시설!! 더보기
    @GET("/app/rooms/{roomId}/facilities")
    fun getFacilities(
        @Path("roomId") roomId : Long
    ): Call<AccommodationFacilityMore>


    @POST("/app/favorites?")
    fun postLikes(
        @Header("Authorization") accessToken : String,
        @Query("roomId") roomId: Long
    ): Call<AccomodationData>

//    @FormUrlEncoded
//    @POST("/app/favorites?region=SEOUL")
//    fun getFavorites(
//        @Header("Authorization") accessToken : String,
//        @Field("region") region : String,
//        @Field("page") page : Int
//    ): Call<FavoriteBackendResponse>

    @GET("/app/favorites?")
    fun getFavorites(
        @Header("Authorization") accessToken : String,
        @Query("region") region : String,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): Call<FavoriteBackendResponse>

}
