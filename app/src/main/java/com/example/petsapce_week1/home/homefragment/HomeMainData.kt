package com.example.petsapce_week1.home.homefragment

import java.io.Serializable

class HomeMainData(val imgList: ArrayList<HomeChildData>, val score:Int, val location:String, val date:Int, val price:Int):
    Serializable