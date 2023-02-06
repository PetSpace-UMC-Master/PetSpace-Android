package com.example.petsapce_week1

import androidx.lifecycle.ViewModel

class EmailViewModel:ViewModel() {


    //flag값을 받을 변수 초기는 false
    private var flag:Boolean = false

    fun ButtonLiveFlag(check:Boolean){
        flag = check

    }
    fun getFlag(): Boolean{
        return flag
    }
}