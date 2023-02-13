package com.example.petsapce_week1.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class ResViewModel : ViewModel() {

    private val _currentValue = MutableLiveData<String>()

    val currentValue: LiveData<String>
        get() = _currentValue

    //초기값 설정
    init {
        _currentValue.value = ""
    }

    fun plusValue(input: String) {
        _currentValue.value = input
    }


    fun minusValue(): String? {
        return _currentValue.value
    }

    //========== 사람수 모달=====================================================================================


    //어른
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


    //아이
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



    //동물
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

    //어디서 베껴온지 모르ㅔㅅ네
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



    //날짜
    val now: LocalDate = LocalDate.now()
    val endDate = now.plusDays(2)

    val curStartDate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val curEndDate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        curStartDate.value = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        curEndDate.value = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getStardDate(nowday:String){
        curStartDate.value  = nowday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getEndDate(nowday:String){
        curEndDate.value  = nowday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }



   /* fun plusAnimal(){
        curCalDate.value  = curCalDate.value?.plus(1)
    }
    fun minusAnimal(){
        curCalDate.value  = curCalDate.value?.minus(1)
//        Log.d("btn1", curAnimal.value.toString())
    }*/





}