package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.HomeResearchRowBinding
import kotlinx.android.synthetic.main.activity_acc_main.view.*
import kotlinx.android.synthetic.main.home_main_row.view.*

class HomeResearchAdapter(var items: ArrayList<HomeResearchData>) :
    RecyclerView.Adapter<HomeResearchAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: HomeResearchData)
//        fun onClick(v: View, position: Int)
    }


    var itemClickListener: OnItemClickListener? = null //초기값 null값


    inner class ViewHolder(val binding: HomeResearchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: HomeResearchData) {
            binding.apply {
                imgLoc.setImageResource(data.imgList)
                textLoc.text = data.location
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HomeResearchRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        /*    holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView?.context, AccMainActivity::class.java)
                intent.putExtra("content", roomIDNext)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
                Log.d("content", roomIDNext.toString())
            }*/

        holder.binding.topcardview.cardElevation = 0f
        holder.bind(items[position])

    }


    override fun getItemCount(): Int {
        return items.size
    }


}



