package com.example.petsapce_week1.accomodation.scroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.databinding.FragmentGoogleBinding

class googleFragment : Fragment() {


    private lateinit var viewModel: GoogleViewModel
    private lateinit var viewBinding:FragmentGoogleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGoogleBinding.inflate(layoutInflater)
        //activity의 setcontentview가 아닌 return값을 주면된다.





        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoogleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}