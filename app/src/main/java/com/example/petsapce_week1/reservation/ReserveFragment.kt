package com.example.petsapce_week1.reservation


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.databinding.FragmentReserveBinding
import com.example.petsapce_week1.tab1_reserved
import com.example.petsapce_week1.tab2_visited

class ReserveFragment : Fragment() {

    lateinit var tab1: tab1_reserved
    lateinit var tab2: tab2_visited

    lateinit var binding : FragmentReserveBinding

    private var tcontext: Context ?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.tcontext = context
    }
//    //토큰 저장 객체
//    val atpref = context?.getSharedPreferences("accessToken", MODE_PRIVATE)
    var accessToken : String ?= null
    lateinit var postaccessToken : String

    override fun onResume() {
        super.onResume()
        getAccessToken()
    }
    private fun getAccessToken() {
        val atpref = requireContext().getSharedPreferences("accessToken", MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        Log.d("accessToken11","$accessToken")
        postaccessToken = accessToken.toString()
        Log.d("함께 갈 곳 토큰 받아와11", "$postaccessToken")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        tab1 = tab1_reserved()
        tab2 = tab2_visited()

/*        val img = arrayOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu5,
            R.drawable.menu4,
            R.drawable.menu6,
            R.drawable.menu7,
            R.drawable.menu8,
            R.drawable.menu9,
        )*/

//        val gridView = view.findViewById<GridView>(R.id.place_gridview)
//        gridView.adapter = accessToken?.let { PlaceGridAdapter(requireContext(), img, it) }

        getAccessToken()
        Log.d("함께 갈 곳 토큰 받아와", "$accessToken")
        binding = FragmentReserveBinding.inflate(layoutInflater)


//        val gridViewAdapter = context?.let { PlaceGridAdapter(it, img) }
//        binding.placeRecyvlerview.adapter = gridViewAdapter

        return binding.root
    }

}