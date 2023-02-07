package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentProfileMenuBinding

class ProfileMenuFragment : Fragment() {
    lateinit var binding: FragmentProfileMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileMenuBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        binding.btnSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_menu, container, false)
    }
}