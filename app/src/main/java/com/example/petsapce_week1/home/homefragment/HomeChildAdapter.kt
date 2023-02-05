package com.example.petsapce_week1.home.homefragment

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.HomeMainRowChildBinding

class HomeChildAdapter(private val items: List<HomeChildData>) : RecyclerView.Adapter<HomeChildAdapter.ChildViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = HomeMainRowChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        // set the adapter to the ViewPager
//        holder.binding.childImg.setImageResource(items[position].childImg)

        Glide.with(holder.itemView)
            .load(items[position].childImg)
//            .fitCenter()
//            .override(200)
            .into(holder.binding.childImg)
//        holder.childRecyclerView.adapter = HomeChildAdapter(items[position].childImg)
    }

    override fun getItemCount(): Int = items.size

    inner class ChildViewHolder(val binding: HomeMainRowChildBinding) : RecyclerView.ViewHolder(binding.root){

    }
}

