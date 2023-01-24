package com.example.petsapce_week1.accomodation.scroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.GifActivity
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.FragmentReviewBinding


class reviewFragment : Fragment() {

    private lateinit var binding:FragmentReviewBinding
    lateinit var adapter: reviewAdapter
    var dataList = ArrayList<reviewData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(layoutInflater)

        initRecyclerView()

        initAddData()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initAddData() {
        dataList.add(reviewData(R.drawable.face,5,"김",1,"품고 기관과 있으랑의 그들은 같이, 청춘 서만 되려니와, 방지하는 있는가?"))
        dataList.add(reviewData(R.drawable.arrow_back,5,"김현아",3,"품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."))
        dataList.add(reviewData(R.drawable.aircon,5,"김현아",3,"품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."))
        dataList.add(reviewData(R.drawable.star3,5,"김현아",3,"품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."))
        dataList.add(reviewData(R.drawable.star3,5,"김현아",3,"품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."))
        dataList.add(reviewData(R.drawable.star3,5,"김현아",3,"품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가? 품고 기관과 있으며, 만물은 곳으로 운다. 사랑의 그들은 같이, 청춘에 서만 되려니와, 방지하는 있는가?..."))
    }

    private fun initRecyclerView() {

        //기존 adapter(recyclerview adpater)
        binding.recyclerview.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        adapter = reviewAdapter(dataList)
        binding.recyclerview.adapter = adapter


        adapter.itemClickListener = object : reviewAdapter.OnItemClickListener {
            override fun OnItemClick(data: reviewData) {
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