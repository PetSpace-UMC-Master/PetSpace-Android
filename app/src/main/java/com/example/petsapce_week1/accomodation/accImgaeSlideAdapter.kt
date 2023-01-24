package com.example.petsapce_week1.accomodation

import android.annotation.SuppressLint
import android.nfc.tech.IsoDep.get
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.accomodation.scroll.reviewData
import com.example.petsapce_week1.databinding.AccMainImageslideBinding
import com.example.petsapce_week1.databinding.AccReviewRowBinding

class accImgaeSlideAdapter(val items: ArrayList<imageSlideData>) : RecyclerView.Adapter<accImgaeSlideAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: imageSlideData)
    }

    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: AccMainImageslideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imgMain.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition]) //?는 null일 수 도 있다고 알려주는 역할
                /* val intent = Intent(this,detailPageActivity::class.java)
                 startActivity(intent)*/
                Log.d("touch3","touch")

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

        holder.binding.apply {
            //이미지는 이런식으로 담아야함.
            imgMain.setImageResource(items[position].imgSlide)
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