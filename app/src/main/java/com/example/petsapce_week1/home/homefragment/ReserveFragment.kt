package com.example.petsapce_week1.home.homefragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.FragmentHomeReserveBinding
import com.example.petsapce_week1.loginrelated.LoginActivity


class ReserveFragment : Fragment() {

    private lateinit var binding:FragmentHomeReserveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeReserveBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.btnAccomo.setOnClickListener {
            val intent = Intent(context, AccMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }




        return binding.root
    }


}