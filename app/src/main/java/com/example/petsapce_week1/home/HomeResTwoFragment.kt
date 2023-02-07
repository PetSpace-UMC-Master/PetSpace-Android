package com.example.petsapce_week1.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentHomeResTwoBinding


class HomeResTwoFragment : Fragment() {
    private lateinit var binding:FragmentHomeResTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeResTwoBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment






        return binding.root
    }

}