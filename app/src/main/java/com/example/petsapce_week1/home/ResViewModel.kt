package com.example.petsapce_week1.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petsapce_week1.home.homefragment.HomeMainAdapter
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ResViewModel : ViewModel() {

    // 결과를 받을 변수, 초기 결과는 0
    var curText = "어느 지역으로 가시나요?"

    // 결과값을 return 하는 함수

    fun setText(input: String) {
        curText = input
        Log.d("text1", curText)
    }

    fun returnText(): String {
        return curText
        Log.d("text23", curText)
    }

    private val _currentValue = MutableLiveData<String>()

    val currentValue: LiveData<String>
        get() = _currentValue

    //초기값 설정
    init {
        _currentValue.value = "어느 지역으로 가시나요?"
    }

    fun plusValue(input: String) {
        _currentValue.value = input
    }


    fun minusValue(): String? {
        return _currentValue.value
    }



}