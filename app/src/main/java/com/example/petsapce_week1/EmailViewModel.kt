package com.example.petsapce_week1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    //초기값 설정
    init {
        _currentValue.value = 0
    }

    fun plusValue(input: Int) {
        _currentValue.value = input
    }


    fun minusValue(): Int? {
        return _currentValue.value
    }
}