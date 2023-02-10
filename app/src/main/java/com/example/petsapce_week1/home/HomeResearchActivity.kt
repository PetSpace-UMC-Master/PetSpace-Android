package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.ActivityHomeResearchBinding
import kotlinx.android.synthetic.main.activity_home_research.*

class HomeResearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeResearchBinding
    lateinit var viewModel: ResViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeResearchBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //viewmodel $$ observer패턴
        viewModel = ViewModelProvider(this).get(ResViewModel::class.java)
        //화면에 바로 뷰바인딩
        val nameObserver = Observer<String> { it ->
            binding.textWhere.text = it.toString()
        }

    /*    val nameObserver2 = Observer<String> { curText ->
            binding.textWhere.text = curText.toString()
        }*/
        //옵저버 이벤트가 생길때마다 알려줌
        viewModel.currentValue.observe(this,nameObserver)
//        viewModel.currentValue.observe(this,nameObserver2)

       /* binding.DataBinding
        binding.textWhere = viewModel*/


        initViewPager()
        initPerson()
        initDataBinding()
        initReset()
//        showProfileDialog()

    }


    @SuppressLint("SetTextI18n")
    private fun initDataBinding() {
        //성인
        val nameObserver = Observer<Int> { curAdult ->
            binding.textPerson.text = "성인 ${curAdult}명, 아동 ${viewModel.curChild.value}명, 반려동물 ${viewModel.curAnimal.value}마리"
        }
        viewModel.curAdult.observe(this, nameObserver)

        //아동
        viewModel.curChild.observe(this, Observer { curChild ->
            binding.textPerson.text = "성인 ${viewModel.curAdult.value}명, 아동 ${curChild}명, 반려동물 ${viewModel.curAnimal.value}마리"
        })

        //동물
        viewModel.curAnimal.observe(this, Observer { curAnimal ->
            binding.textPerson.text = "성인 ${viewModel.curAdult.value}명, 아동 ${viewModel.curChild.value}명, 반려동물 ${curAnimal}마리"
        })
    }

    //fragment dialog불러오기
    private fun initPerson() {
        binding.textPerson.setOnClickListener {
            val dialog = PersonDialogFragment()
            dialog.show(supportFragmentManager,"PersonDialog")
        }
    }


    private fun initReset() {
        binding.apply {
            reset.setOnClickListener {
                textWhere.text = "어느 지역으로 가시나요?"
                viewModel.curAdult.postValue(0)
                viewModel.curChild.postValue(0)
                viewModel.curAnimal.postValue(0)
            }
        }
    }

    private fun initViewPager() {
        // viewpager && adapter
        val mainVPAdapter = HomeResVPAdapter(this)
        val viewPager = binding.viewpager
        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = mainVPAdapter
        dotsIndicator.attachTo(viewPager)    }
/*
    override fun onChanged(t: String?) {
//        val text2 = viewModel.minusValue()
        binding.textWhere.text = viewModel.currentValue.value
    }*/


}