package com.example.petsapce_week1.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// networkservice파일을 열고 retrofit에 사용할 인터페이스
interface NetworkService {

    //get은 서버와 연동할때 이런 방식으로 해다라라는 의미
    @GET("posters/useditem")
    fun getList(
        //query는 서버에 전달되는 데이터
        @Query("q") q:String?,
        @Query("page") page:Long,
        @Query("pageSize") pageSize:Int

    ):Call<UserListModel>

}

