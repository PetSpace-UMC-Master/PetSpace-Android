package com.example.petsapce_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petsapce_week1.databinding.ActivityGangwonBinding

class JejuActivity : AppCompatActivity() {
    lateinit var binding : ActivityGangwonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGangwonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}