package com.example.petsapce_week1.accomodation.scroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityAccHostBinding

class AccHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccHostBinding.inflate(layoutInflater)
        setContentView(binding.root)


       /* binding.button.setOnClickListener {
            val intent = Intent(this,GifActivity::class.java)
            startActivity(intent)
        }*/
    }
}