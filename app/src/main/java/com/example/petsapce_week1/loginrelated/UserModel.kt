package com.example.petsapce_week1.loginrelated

//JSON data를 받아올 데이터 클래스

data class UserModel_Kakao(
    //카카오 로그인에서 백엔드로 보내는 데이터
    val accessToken : String ?= null
)

data class UserModel_General(
    //일반 로그인에서 백엔드로 보내는 데이터
    var id : String ?= null,
    var pw : String ?= null
)

data class LoginBackendResponse(
    val email : String,
    //200: 성공, 300,400: 에러
    val accessToken : String,
    val refreshToken : String
)


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
