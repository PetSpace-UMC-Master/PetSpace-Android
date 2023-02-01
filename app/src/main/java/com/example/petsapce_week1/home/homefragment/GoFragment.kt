package com.example.petsapce_week1.home.homefragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityPlacetogoBinding

class GoFragment : Fragment() {

    private lateinit var binding: ActivityPlacetogoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityPlacetogoBinding.inflate(layoutInflater)




        return binding.root
    }


}