package com.example.petsapce_week1.retrofit

import com.google.gson.annotations.SerializedName

//서버의 데이터를 저장하는 클래스, 여기서는 여러 유저 정보들만 저장
class UserListModel{
    var Users:MutableList<UserModel>? = null
}