package com.example.petsapce_week1.vo



//회원가입 시 보내는 데이터
data class SignupData(
    val birth: String,
    val checkedPassword: String,
    val email: String,
    val marketingAgreement: Boolean,
    val nickname: String,
    val password: String,
    val username: String
)

// 이메일 중복확인 시 보내는 데이터
data class EmailCheckData(
    val email : String
)