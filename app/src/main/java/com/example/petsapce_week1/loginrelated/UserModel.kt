package com.example.petsapce_week1.loginrelated

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//JSON data를 받아올 데이터 클래스

data class UserModelKakao(
    //카카오 로그인에서 백엔드로 보내는 데이터
    val accessToken : String ?= null
)

data class UserModelGeneral(
    //일반 로그인에서 백엔드로 보내는 데이터
    var email : String ?= null,
    var password : String ?= null
)

data class UserToken(
    var accessToken : String,
)

data class LoginBackendResponse(
    val isSuccess : Boolean ?= null,
    //200: 성공, 300,400: 에러'
    val responseCode : String ?= null,
    val responseMessage : String ?= null,
    val result : Result ?= null,//하나의 객체 List XX
){
    data class Result(
        val email : String ?= null,
        val accessToken: String ?= null,
        val refreshToken: String ?= null
    )
}


// [GET] /app/users/{id}
data class UserDetailResponse(
    val isSuccess : Boolean ?= null,
    //200: 성공, 300,400: 에러'
    val responseCode : String ?= null,
    val responseMessage : String ?= null,
    val result : Result ?= null,//하나의 객체 List XX
){
    data class Result(
        val email : String ?= null,
        val username: String ?= null,
        val nickname : String ?= null,
        val birth : String ?= null
    )
}

/* 이렇게도 쓸 수 있음
data class DataclassEx(
    val `data`: List<Data>,
    val message: String,
    val status: Int,
    val isLogin: Boolean
)
{
    data class Data(
        val id: Int,
        val image: String,
        val user_id: Int,
        val user_img: String,
        val user_name: String
    )
}
 */
