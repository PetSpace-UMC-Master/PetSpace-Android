package com.example.petsapce_week1.placetogo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentPlaceToGoBinding

class PlaceToGoFragment : Fragment() {

    lateinit var binding : FragmentPlaceToGoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_place_to_go, container, false)

        val img = arrayOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu5,
            R.drawable.menu4,
            R.drawable.menu6,
            R.drawable.menu7,
            R.drawable.menu8,
            R.drawable.menu9,
        )

        val gridView = view.findViewById<GridView>(R.id.place_gridview)
        gridView.adapter = PlaceGridAdapter(requireContext(), img)

        binding = FragmentPlaceToGoBinding.inflate(layoutInflater)



//        val gridViewAdapter = context?.let { PlaceGridAdapter(it, img) }
//        binding.placeRecyvlerview.adapter = gridViewAdapter

        return view
    }

}