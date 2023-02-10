package com.example.petsapce_week1.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.FragmentHomeResOneBinding


class HomeResOneFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeResOneBinding
    lateinit var viewModel: ResViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeResOneBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        // viewModel의 주최를 MainActivityViewModel로 설정
        viewModel = ViewModelProvider(requireActivity()).get(ResViewModel::class.java)

        initButton()

        return binding.root
    }


    fun initButton() {
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
        binding.b3.setOnClickListener(this)
        binding.b4.setOnClickListener(this)
        binding.b5.setOnClickListener(this)
        binding.b6.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            binding.b1.id -> {
                viewModel.plusValue("서울")
//                viewModel.setText("서울")

            }
            binding.b2.id -> {
                viewModel.plusValue("제주")

            }
            binding.b3.id -> {
                viewModel.plusValue("춘천")

            }
            binding.b4.id -> {
                viewModel.plusValue("속초")
            }
            binding.b5.id -> {
                viewModel.plusValue("전주")
            }
            binding.b6.id -> {
                viewModel.plusValue("부산")
            }
        }
    }


}

