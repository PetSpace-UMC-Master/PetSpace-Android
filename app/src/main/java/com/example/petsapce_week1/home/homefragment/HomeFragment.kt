package com.example.petsapce_week1.home.homefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accomodation.scroll.reviewAdapter
import com.example.petsapce_week1.accomodation.scroll.reviewData
import com.example.petsapce_week1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    var dataList = ArrayList<HomeMainData>()
    lateinit var adapter: HomeMainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        initAddData()
        initRecyclerView()



        binding.im1.setOnClickListener {
            val intent = Intent(context,GifActivity::class.java)
            startActivity(intent)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initAddData() {
        dataList.add(HomeMainData(R.drawable.home2,5,"종로구, 서울",11,5000))
        dataList.add(HomeMainData(R.drawable.home2,5,"서초구",11,5000))
        dataList.add(HomeMainData(R.drawable.home2,5,"서초구",11,1))
        dataList.add(HomeMainData(R.drawable.home2,5,"서초구",11,5000))
        dataList.add(HomeMainData(R.drawable.home2,5,"서초구",11,5000))
    }

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerviewMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        adapter = HomeMainAdapter(dataList)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.isNestedScrollingEnabled = false


        adapter.itemClickListener = object : HomeMainAdapter.OnItemClickListener {
            override fun OnItemClick(data: HomeMainData) {
                /*  Toast.makeText(getActivity(),"show", Toast.LENGTH_SHORT).show()
                   val intent = packageManager.getLaunchIntentForPackage(data.appackname)
                   startActivity(intent)*/
                val intent = Intent(context, GifActivity::class.java)
                startActivity(intent)

                Log.d("test", "test")
            }
        }
    }



}