package com.example.petsapce_week1.placetogo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentNologinPlaceToGoBinding
import com.example.petsapce_week1.loginrelated.LoginActivity

class NoLoginPlacetogoFragment : Fragment() {

    private lateinit var binding : FragmentNologinPlaceToGoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNologinPlaceToGoBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            context?.startActivity(intent)
        }

        return binding.root
    }
}