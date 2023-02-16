package com.example.petsapce_week1.reservationbcw

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.*
import com.example.petsapce_week1.home.Home2ChildAdapter
import com.example.petsapce_week1.home.Home2MainData
import com.example.petsapce_week1.home.homefragment.HomeChildAdapter
import com.example.petsapce_week1.home.homefragment.HomeMainData
import com.example.petsapce_week1.vo.HomeResponse
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
//import kotlinx.android.synthetic.main.activity_acc_main.view.*
//import kotlinx.android.synthetic.main.home_main_row.view.*
import java.text.DecimalFormat

class reserveMainAdapter(var items: ArrayList<reserveMainData>) :
    RecyclerView.Adapter<reserveMainAdapter.ViewHolder>() {

/*
    interface OnItemClickListener {
        fun OnItemClick(data: reserveMainData)
//        fun onClick(v: View, position: Int)
    }


    var itemClickListener: OnItemClickListener? = null //초기값 null값

*/

    inner class ViewHolder(val binding: ReservationMainRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: reserveMainData) {
            val cut = String.format("%.2f", data.score)
            val priceCut = DecimalFormat("#,###")
            val price = priceCut.format(data.price)

            val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = reserveChildAdapter(data.imgList)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)


            binding.apply {
                textPrice.text = "₩${price} / 박"
                textDate.text = data.date.toString()
           /*     textLoc.text = data.location
                textScore.text = cut
                textReview.text = "("+data.review.toString()+")"*/
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ReservationMainRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val roomIDNext = items[position].roomID

    /*    holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,AccMainActivity::class.java)
            intent.putExtra("content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("content",roomIDNext.toString())
        }*/


        holder.binding.apply {
            topcardview.cardElevation = 0f
            btnHeart.setOnClickListener {
                btnHeart.isSelected = btnHeart.isSelected != true
            }

        }
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }


}



