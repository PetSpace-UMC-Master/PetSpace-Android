package com.example.petsapce_week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivityReservationBinding

class ReservationActivity : AppCompatActivity() {
    lateinit var binding : ActivityReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.reservationLayout.setOnClickListener {
            val intent = Intent(this, Reservation2Activity::class.java)
            startActivity(intent)
        }
    }
}