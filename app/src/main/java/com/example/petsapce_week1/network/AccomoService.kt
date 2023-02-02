package com.example.petsapce_week1.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.petsapce_week1.vo.AccommodationFacilityMore
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import retrofit2.Call
import retrofit2.http.*

interface AccomoService {
    @GET("/app/room/{roomId}")
    fun getRoomDetail(
        @Path("roomId") roomId : Long = 1
        //@Body jsonParams : AccomodationRoomData
    ):Call<AccomodationData>

    @GET("/app/room/{roomId}/facilities")
    fun getFacilities(
        @Path("roomId") roomId : Long = 1
    ): Call<AccommodationFacilityMore>

    @POST("/app/favorites?roomId=1")
    fun postLikes(
        @Header("Authorization") accessToken : String
    ): Call<AccomodationData>
}
