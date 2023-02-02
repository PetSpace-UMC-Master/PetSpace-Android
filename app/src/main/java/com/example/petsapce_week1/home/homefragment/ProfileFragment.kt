package com.example.petsapce_week1.home.homefragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.R
import com.example.petsapce_week1.SettingsActivity
import com.example.petsapce_week1.databinding.ActivityProfileMenuBinding


class ProfileFragment : Fragment() {
    lateinit var binding : ActivityProfileMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ActivityProfileMenuBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.btnSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }


}