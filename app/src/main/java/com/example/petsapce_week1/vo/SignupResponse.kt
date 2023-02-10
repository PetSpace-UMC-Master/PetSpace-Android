package com.example.petsapce_week1.vo


//회원 가입 시 받는 데이터
data class SignupResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
){
    data class Result(
        val birth: String,
        val email: String,
        val id: Int,
        val nickname: String,
        val profileImage: String,
        val username: String
    )
}

//이메일 중복체크 호출 시 받는 데이터
data class EmailCheckResponse(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: Result
)