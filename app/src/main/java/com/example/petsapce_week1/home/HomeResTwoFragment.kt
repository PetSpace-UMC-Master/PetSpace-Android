package com.example.petsapce_week1.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentHomeResTwoBinding


class HomeResTwoFragment : Fragment(), View.OnClickListener {
    private lateinit var binding:FragmentHomeResTwoBinding
    lateinit var viewModel: ResViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeResTwoBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(requireActivity()).get(ResViewModel::class.java)


        initButton()

        return binding.root
    }

    fun initButton() {
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            binding.b1.id -> {
                viewModel.plusValue("광주")
//                viewModel.setText("서울")

            }
            binding.b2.id -> {
                viewModel.plusValue("대구")

            }

        }
    }


}