package com.example.petsapce_week1.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReservationTabBinding


class ReservationTabFragment : Fragment() {

    var name = "예약 화면"
    lateinit var binding : FragmentReservationTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReservationTabBinding.inflate(layoutInflater)
        binding.tvMenu.text = name
        return binding.root
    }

}