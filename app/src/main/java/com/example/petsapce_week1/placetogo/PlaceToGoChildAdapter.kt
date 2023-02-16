package com.example.petsapce_week1.placetogo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.PlacetogoChildListBinding

class PlaceToGoChildAdapter(private val items: List<String>) : RecyclerView.Adapter<PlaceToGoChildAdapter.ChildViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = PlacetogoChildListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = items[position]
        //holder.textView.text = item.text

        //holder.binding.viewpagerchildImg.setImageResource(item)

        Glide.with(holder.itemView)
            .load(item)
            .into(holder.binding.viewpagerchildImg)
//        binding.frameFacility.tvFac1.text = reviewList[0].facname
//        Glide.with(this@AccMainActivity)
//            .load(reviewList[0].imgUrl)
//            .into(binding.frameFacility.imgFac1)
//        binding.frameFacility.tvFac2.text = reviewList
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ChildViewHolder(val binding: PlacetogoChildListBinding) : RecyclerView.ViewHolder(binding.root){

    }
}