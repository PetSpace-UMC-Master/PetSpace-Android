package com.example.petsapce_week1.accommodation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.databinding.AccMainImageslideBinding

class accImgaeSlideAdapter(val items: ArrayList<imageSlideData>) : RecyclerView.Adapter<accImgaeSlideAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: String)
    }

    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: AccMainImageslideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imgMain.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition].imgSlide) //?는 null일 수 도 있다고 알려주는 역할
                //itemClickListener?.OnItemClick(items[adapterPosition])
                /* val intent = Intent(this,detailPageActivity::class.java)
                 startActivity(intent)*/
                Log.d("숙소 touch3","touch")

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AccMainImageslideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(items[position].imgSlide)
//            .fitCenter()
            .override(200)
            .into(holder.binding.imgMain)
        Log.d("숙소 position", "$position")
        Log.d("숙소 item0", "${items[0]}")
        holder.binding.apply {
            //이미지는 이런식으로 담아야함.
            //imgMain.setImageResource(items[position].imgSlide)
          /*  textName.text = items[position].name
            textDate.text = items[position].date.toString()+"주 전"
            textDetail.text = items[position].text*/
//            textViewDifficulty.text= "난이도 ${position+1}"
        }

    }


    override fun getItemCount(): Int {
        return items.size
    }


}