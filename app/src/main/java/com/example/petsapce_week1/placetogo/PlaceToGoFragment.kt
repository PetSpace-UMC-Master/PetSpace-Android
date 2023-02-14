package com.example.petsapce_week1.placetogo


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentPlaceToGoBinding

class PlaceToGoFragment : Fragment() {

    lateinit var binding : FragmentPlaceToGoBinding

    private var tcontext: Context ?= null

    var accessToken : String ?= null

    override fun onResume() {
        super.onResume()
        getAccessToken()
    }
    private fun getAccessToken() {
        val atpref = requireContext().getSharedPreferences("accessToken", MODE_PRIVATE)
        accessToken = atpref.getString("accessToken", "default")
        Log.d("함께 갈 곳 액토","$accessToken")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.tcontext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPlaceToGoBinding.inflate(layoutInflater)

        val img = arrayOf(
            R.drawable.menu1,
            R.drawable.frame_busan,
            R.drawable.menu2,
            R.drawable.menu8,
            R.drawable.menu5,
            R.drawable.menu4,
            R.drawable.menu6,
            R.drawable.menu7,
        )
        getAccessToken()
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
        if(accessToken != null){
            val gridVew = binding.placeGridview
            gridVew.adapter =
                tcontext?.let { PlaceGridAdapter(parentFragmentManager, it, accessToken = accessToken!!, img_list = img) }
            //adapter = tcontext?.let { PlaceGridAdapter(it, img, accessToken!!) }!!
//            binding.placeGridview.adapter =
//                tcontext?.let { PlaceGridAdapter(context = requireContext(), accessToken = accessToken!!, img_list = img) }
            //binding.placeGridview.adapter = adapter
            Log.d("함께 갈 곳", "안비었음, ${img}")
        }
        else{
            Log.d("함께 갈 곳", "비었음")

//            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            val newFragment = NoLoginPlacetogoFragment()
//            fragmentTransaction.replace(R.id.placetogoLayout, newFragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
        }

        //adapter = PlaceToGoRegionAdapter(dataList)
        //        binding.recyclerviewMain.adapter = adapter
        //        binding.recyclerviewMain.isNestedScrollingEnabled = true
//        adapter = context?.let { accessToken?.let { it1 -> PlaceGridAdapter(it, img, it1) } }!!

        return binding.root
    }

}