package com.example.petsapce_week1.reservationbcw

import com.example.petsapce_week1.home.homefragment.HomeChildData
import java.io.Serializable

class reserveMainData(
    val imgList: ArrayList<reserveChildData>, val score: Float, val location:String, val date:String, val price:Int,
    val review:Int, val roomID:Int):
    Serializable