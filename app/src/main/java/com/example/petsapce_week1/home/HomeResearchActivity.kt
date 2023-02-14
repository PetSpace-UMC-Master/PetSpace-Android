package com.example.petsapce_week1.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.ActivityHomeResearchBinding

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

        val nameObserver2 = Observer<String> { curText ->
            binding.textWhere.text = curText.toString()
        }
        //옵저버 이벤트가 생길때마다 알려줌
        viewModel.currentValue.observe(this,nameObserver)
        viewModel.currentValue.observe(this,nameObserver2)

       /* binding.DataBinding
        binding.textWhere = viewModel*/


        // viewpager && adapter
        val mainVPAdapter = HomeResVPAdapter(this)
        val viewPager = binding.viewpager
        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = mainVPAdapter
        dotsIndicator.attachTo(viewPager)

        initReset()
        initPerson()
//        showProfileDialog()

    }

    private fun initPerson() {
        binding.textPerson.setOnClickListener {
            showProfileDialog()
        }
    }

    private fun showProfileDialog() {
        PersonDialog(this) {
//            viewModel.setName(it)
        }.show()
    }

    private fun initReset() {
        binding.apply {
            reset.setOnClickListener {
                textWhere.text = "어느 지역으로 가시나요?"
            }
        }
    }
/*
    override fun onChanged(t: String?) {
//        val text2 = viewModel.minusValue()
        binding.textWhere.text = viewModel.currentValue.value
    }*/


}