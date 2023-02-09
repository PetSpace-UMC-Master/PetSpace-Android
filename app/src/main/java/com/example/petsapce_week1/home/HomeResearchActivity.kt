package com.example.petsapce_week1.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        //viewmodel $$ observer
        viewModel = ViewModelProvider(this).get(ResViewModel::class.java)
//        viewModel.currentValue.observe(this, this)


        val nameObserver = Observer<String> { it ->
            binding.textWhere.text = it.toString()
        }
        viewModel.currentValue.observe(this,nameObserver)
       /* binding.DataBindingU
        binding.textWhere = viewModel*/


        // viewpager && adapter
        val mainVPAdapter = HomeResVPAdapter(this)
        val viewPager = binding.viewpager
        val dotsIndicator = binding.dotsIndicator
        viewPager.adapter = mainVPAdapter
        dotsIndicator.attachTo(viewPager)

        initReset()

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