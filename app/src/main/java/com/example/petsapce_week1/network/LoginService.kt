package com.example.petsapce_week1.network

import com.example.petsapce_week1.loginrelated.LoginBackendResponse
import com.example.petsapce_week1.loginrelated.UserModelGeneral
import com.example.petsapce_week1.loginrelated.UserModelKakao
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationData
import com.example.petsapce_week1.vo.accomo_datamodel.AccomodationRoomData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    //Kakao Login
    @POST("/oauth/kakao/")
    fun postAccessToken(
        //@Header("access_token") token: String
        @Body jsonParams: UserModelKakao

    ): Call<LoginBackendResponse>

    //General Login
    @POST("/app/login")
    fun userLogin(
        @Body jsonParams: UserModelGeneral,
    ): Call<LoginBackendResponse>

}

//숙소 상세정보 인터페이스
interface AccomoService {
    @POST("/app/room/:roomId")
    fun getRoomDetails(
        @Body jsonParams : AccomodationRoomData
    ): Call<AccomodationData>
}

