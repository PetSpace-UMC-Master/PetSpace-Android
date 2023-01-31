package com.example.petsapce_week1.accommodation.scroll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.FacilitiesCategoryListBinding
import com.example.petsapce_week1.vo.FacilityReceived

class AccFacilitiesCategoryAdapter(val item: MutableList<FacilityReceived>) : RecyclerView.Adapter<AccFacilitiesCategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ViewHolder {

        val binding = FacilitiesCategoryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }
    inner class ViewHolder(var binding: FacilitiesCategoryListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategory.text = item[position].category
        holder.binding.rvFacilitiesList.adapter = AccFacilitiesAdapter(item[position].facilities)
        holder.binding.rvFacilitiesList.layoutManager = LinearLayoutManager(holder.binding.rvFacilitiesList.context)
    }

}