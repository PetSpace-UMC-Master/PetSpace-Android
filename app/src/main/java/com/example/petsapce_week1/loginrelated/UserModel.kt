package com.example.petsapce_week1.loginrelated

//JSON data를 받아올 데이터 클래스

data class UserModel(
    //@SerializedName("status") val status: Int
    // => 코드상 변수명과 api에 정의된 이름을 다르게 할 때 사용
    val accessToken : String ?= null
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
