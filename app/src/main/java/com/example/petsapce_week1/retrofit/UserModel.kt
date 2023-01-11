package com.example.petsapce_week1.retrofit

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName


//json 정보를 담을 모델 클래스
//서버에서 넘어오는 각 user의 세부 정보를 저장
data class UserModel(
    var id:String,
    //프로젝트내의 변수명과 db의 키값이 다를때 serialized해서 명시 해주면 됨
    @SerializedName("first_name")
    var firstName:String,
    var lastName:String,
    var avatar:String,
    var avatarBitmap: Bitmap


)