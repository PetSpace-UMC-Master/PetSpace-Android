package com.example.petsapce_week1.reservation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.PlacetogoChildListBinding
import com.example.petsapce_week1.databinding.ReservationVisitedTabChildListBinding
import com.example.petsapce_week1.placetogo.PlaceToGoChildAdapter


class VisitedTabChildAdapter(private val items: List<String>) : RecyclerView.Adapter<VisitedTabChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        Log.d("예약 왜 안될까?","2 child")
        val binding = ReservationVisitedTabChildListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChildViewHolder(val binding: ReservationVisitedTabChildListBinding) : RecyclerView.ViewHolder(binding.root){

    }

}