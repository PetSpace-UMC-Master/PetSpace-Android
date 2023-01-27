package com.example.petsapce_week1.home.homefragment

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.AccReviewRowBinding
import com.example.petsapce_week1.databinding.HomeMainRowBinding

class HomeMainAdapter(val items: ArrayList<HomeMainData>) : RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: HomeMainData)
    }

    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: HomeMainRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition]) //?는 null일 수 도 있다고 알려주는 역할

                Log.d("touch3","touch")

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HomeMainRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            //이미지는 이런식으로 담아야함.
            imgMain.setImageResource(items[position].img)
            textLoc.text = items[position].location
            textScore.text = items[position].score.toString()
            textDate.text = items[position].date.toString()
            textPrice.text = "₩"+items[position].price.toString()+" / 박"
//            textViewDifficulty.text= "난이도 ${position+1}"
        }


    }


    override fun getItemCount(): Int {
        return items.size
    }


}