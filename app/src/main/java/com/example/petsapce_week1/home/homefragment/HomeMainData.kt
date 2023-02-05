package com.example.petsapce_week1.home.homefragment

import java.io.Serializable

class HomeMainData(
    val imgList: ArrayList<HomeChildData>, val score: Float, val location:String, val date:String, val price:Int,
    val review:Int):
    Serializable