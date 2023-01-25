package com.example.petsapce_week1.accommodation.scroll

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentFacilitiesMoreBinding
import com.example.petsapce_week1.network.AccomoService
import com.example.petsapce_week1.network.RetrofitHelper
import retrofit2.Retrofit

class AccFacilityMoreFragment : Fragment() {

    private lateinit var binding : FragmentFacilitiesMoreBinding
    //백엔드 서버 연동
    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : AccomoService = retrofit.create(AccomoService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacilitiesMoreBinding.inflate(layoutInflater)

        binding.btnFacilitesClose.setOnClickListener {
            val intent = Intent(context, AccFacilityMoreFragment::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}