package com.example.petsapce_week1.home.homefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        //edittext가 아니라 constraint로 구현해야할듯
        binding.editTextMainTop.setOnClickListener {
            val intent = Intent(context,GifActivity::class.java)
            startActivity(intent)
        }

        binding.btnEdittext.setOnClickListener {
            val intent = Intent(context,GifActivity::class.java)
            startActivity(intent)
        }


        // Inflate the layout for this fragment
        return binding.root
    }


}