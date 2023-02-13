package com.example.petsapce_week1.home

import com.example.petsapce_week1.home.homefragment.HomeChildData
import java.io.Serializable

class Home2MainData(
    val imgList: ArrayList<Home2ChildData>, val score: Float, val location:String, val date:String, val price:Int,
    val review:Int, val roomID:Int):
    Serializable