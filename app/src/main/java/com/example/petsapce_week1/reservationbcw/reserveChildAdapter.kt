package com.example.petsapce_week1.reservationbcw

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.R
import com.example.petsapce_week1.databinding.Home2MainRowChildBinding
import com.example.petsapce_week1.databinding.HomeMainRowChildBinding
import com.example.petsapce_week1.databinding.ReservationMainRowChildBinding
import com.example.petsapce_week1.home.Home2ChildData
import com.example.petsapce_week1.home.homefragment.HomeChildData

class reserveChildAdapter(private val items: List<reserveChildData>) : RecyclerView.Adapter<reserveChildAdapter.ChildViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ReservationMainRowChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        // set the adapter to the ViewPager
//        holder.binding.childImg.setImageResource(items[position].childImg)

        Glide.with(holder.itemView)
            .load(items[position].childImg)
//            .override(200)
            .into(holder.binding.viewpagerchildImg)
//        holder.childRecyclerView.adapter = HomeChildAdapter(items[position].childImg)
    }

    override fun getItemCount(): Int = items.size

    inner class ChildViewHolder(val binding: ReservationMainRowChildBinding) : RecyclerView.ViewHolder(binding.root){

    }
}

