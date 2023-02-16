package com.example.petsapce_week1.reservationbcw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentRightBinding


class RightFragment : Fragment() {
    lateinit var binding:FragmentRightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRightBinding.inflate(layoutInflater)



        // Inflate the layout for this fragment
        return binding.root
    }

}