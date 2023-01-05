package com.example.petspace3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petspace3.databinding.ActivityMainBinding
import com.example.petspace3.databinding.ActivityReadMore1Binding

class TermsContentActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityReadMore1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadMore1Binding.inflate(layoutInflater)

        // 왼쪽 상단 x 버튼 클릭 시 이전 화면(manifest에 parent)
        binding.close.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}