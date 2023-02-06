package com.example.petsapce_week1.accommodation.scroll

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.FacilitiesItemListBinding
import com.example.petsapce_week1.vo.accomo_datamodel.Facility

//class AccFacilityMoreAdapter(val items: MutableList<FacilityData>) : RecyclerView.Adapter<AccFacilityMoreAdapter.ViewHolder>() {
class AccFacilitiesAdapter(val item: List<Facility>) : RecyclerView.Adapter<AccFacilitiesAdapter.ViewHolder>() {

    inner class ViewHolder (val binding : FacilitiesItemListBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.d("Im here", "들어왔어요크리에이트뷰홀더")
        val binding = FacilitiesItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("${position+1} 번째 편의시설", "${item[position]}")
        Glide.with(holder.itemView)
            .load(item[position].facilityImageUrl)
            .fitCenter()
            .override(200)
            .into(holder.binding.imgFac)

        holder.binding.tvFac.text = item[position].facilityName
    }

    override fun getItemCount(): Int {
//        return items.size
        return item.size
    }
}