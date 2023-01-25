package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccomoService {
    @POST("/app/room/:roomId/")
    fun getRoomDetail(
        @Body jsonParams : AccomodationRoomData
    ):Call<AccomodationData>
}
