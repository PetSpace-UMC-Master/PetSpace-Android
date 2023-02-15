package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentProfileMenuBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.review.ReviewPostActivity

class ProfileMenuFragment : Fragment() {
    lateinit var binding: FragmentProfileMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileMenuBinding.inflate(layoutInflater)

        binding.btnSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.btnProfileCy.setOnClickListener {
            val intent = Intent(context, ReviewPostActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}