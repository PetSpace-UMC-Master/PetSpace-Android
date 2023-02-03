package com.example.petsapce_week1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.ActivityHomeOnlyfortestBinding
import com.example.petsapce_week1.loginrelated.LoginActivity
import com.example.petsapce_week1.placetogo.PlaceToGoFragment

class TestMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeOnlyfortestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeOnlyfortestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccomo.setOnClickListener {
            val intent = Intent(this@TestMainActivity, AccMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@TestMainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlacetogo.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val placeFragment = PlaceToGoFragment()
            //fragmentTransaction.remove(supportFragmentManager.findFragmentById(R.id.fragmentview)!!)
            fragmentTransaction.replace(R.id.fragmentview, placeFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}
