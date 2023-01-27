package com.example.petsapce_week1.loginrelated

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
        val refreshToken : String ?= null
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
