package com.example.petsapce_week1.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.ActivityHomeBinding
import com.example.petsapce_week1.home.homefragment.GoFragment
import com.example.petsapce_week1.home.homefragment.HomeFragment
//import com.example.petsapce_week1.home.homefragment.HomeFragment
import com.example.petsapce_week1.home.homefragment.ProfileFragment
import com.example.petsapce_week1.home.homefragment.ReserveFragment
import com.example.petsapce_week1.placetogo.PlaceToGoFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_heart -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, PlaceToGoFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_reserve -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ReserveFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ProfileFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }



    }
}