package com.example.petsapce_week1.network

import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.http.*

interface homeAPI {
    // @Multipart
    @GET("/app/rooms")
    fun get_rooms(

    ): Call<HomeResponse>


    @GET("/app/rooms/")
    fun get_priceDesc(
        @Query("PRICE_DESC") price_desc: String
    ): Call<HomeResponse>

//    PRICE_ASC

    @GET("/app/rooms/")
    fun get_priceAsc(
        @Query("PRICE_ASC") price_asc: String
    ): Call<HomeResponse>

    @GET("/app/rooms/")
    fun get_reviewDesc(
        @Query("REVIEW_COUNT_DESC") review_desc: String
    ): Call<HomeResponse>

    @GET("/app/rooms?sortBy=PRICE_DESC")
    fun getPost2(
    ) : Call<HomeResponse>


   /* @GET("/app/rooms/")
    fun get_page(
        @Query("page") page: Int
    ): Call<HomeResponse>*/


  /*  @GET("/app/rooms/{roomId}")
    fun getRoomDetail(
        @Path("roomId") roomId: Long = 16
        //@Body jsonParams : AccomodationRoomData
    ): Call<HomeResponse>
*/
}