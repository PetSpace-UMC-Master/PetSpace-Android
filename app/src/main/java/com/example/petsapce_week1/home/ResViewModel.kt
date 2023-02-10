package com.example.petsapce_week1.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.model.User

class ResViewModel : ViewModel() {

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

    //========== 사람수 모달=====================================================================================


    val curAdult: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        curAdult.value = 0
    }

    fun plusAdult(){
        curAdult.value  = curAdult.value?.plus(1)
    }
    fun minusAdult(){
        curAdult.value  = curAdult.value?.minus(1)
    }

    val curChild = MutableLiveData<Int>()


    init {
        curChild.value = 0
    }

    fun plusChild(){
        curChild.value  = curChild.value?.plus(1)
    }
    fun minusChild(){
        curChild.value  = curChild.value?.minus(1)
    }


    val curAnimal: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        curAnimal.value = 0
    }

    fun plusAnimal(){
        curAnimal.value  = curAnimal.value?.plus(1)
    }
    fun minusAnimal(){
        curAnimal.value  = curAnimal.value?.minus(1)
//        Log.d("btn1", curAnimal.value.toString())
    }

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
    }

    fun increaseScore() {
        _score.value = (_score.value)?.plus(1)
    }





}