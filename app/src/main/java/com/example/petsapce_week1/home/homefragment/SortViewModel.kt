package com.example.petsapce_week1.home.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petsapce_week1.network.RetrofitHelperHome
import com.example.petsapce_week1.network.homeAPI
import com.example.petsapce_week1.vo.HomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SortViewModel : ViewModel() {
    var retrofit: Retrofit = RetrofitHelperHome.getRetrofitInstance()
    var api: homeAPI = retrofit.create(homeAPI::class.java)
    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter



    private val _currentValue = MutableLiveData<String>()

    val currentValue: LiveData<String>
        get() = _currentValue

    //초기값 설정
    init {
        Log.d("teext", "myNumberViewModel - 생성자 호출")
        _currentValue.value = "어느 지역으로 가시나요?"
    }

    fun plusValue(input: String) {
        _currentValue.value = input

    }


    fun minusValue(): String? {
        return _currentValue.value
    }




}