package com.example.petsapce_week1.placetogo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.PlacetogoChildListBinding

class PlaceToGoChildAdapter(private val items: List<String>) : RecyclerView.Adapter<PlaceToGoChildAdapter.ChildViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = PlacetogoChildListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = items[position]
        //holder.textView.text = item.text

    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ChildViewHolder(val binding: PlacetogoChildListBinding) : RecyclerView.ViewHolder(binding.root){

    }
}