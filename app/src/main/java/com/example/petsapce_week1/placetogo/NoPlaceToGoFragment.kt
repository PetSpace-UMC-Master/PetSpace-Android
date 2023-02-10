package com.example.petsapce_week1.placetogo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentNoPlaceToGoBinding
import com.example.petsapce_week1.databinding.FragmentNologinPlaceToGoBinding
import com.example.petsapce_week1.loginrelated.LoginActivity

class NoPlaceToGoFragment : Fragment() {

    private lateinit var binding : FragmentNoPlaceToGoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoPlaceToGoBinding.inflate(layoutInflater)

        return binding.root
    }
}