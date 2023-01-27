package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.databinding.AccReviewRowBinding

class reviewAdapter(val items: ArrayList<reviewData>) : RecyclerView.Adapter<reviewAdapter.ViewHolder>() {

    interface OnItemClickListener  {
        fun OnItemClick(data: reviewData)
    }

    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: AccReviewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.accReview.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition]) //?는 null일 수 도 있다고 알려주는 역할
                /* val intent = Intent(this,detailPageActivity::class.java)
                 startActivity(intent)*/
                Log.d("touch3","touch")

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AccReviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            //이미지는 이런식으로 담아야함.
            imgFace.setImageResource(items[position].img)
            textName.text = items[position].name
            textDate.text = items[position].date
            textDetail.text = items[position].text
            when (items[position].star) {
                1 -> {
                    imgStar1.visibility = View.VISIBLE
                }
                2 -> {
                    imgStar1.visibility = View.VISIBLE
                    imgStar2.visibility = View.VISIBLE
                }
                3 -> {
                    imgStar1.visibility = View.VISIBLE
                    imgStar2.visibility = View.VISIBLE
                    imgStar3.visibility = View.VISIBLE
                }
                4 -> {
                    imgStar1.visibility = View.VISIBLE
                    imgStar2.visibility = View.VISIBLE
                    imgStar3.visibility = View.VISIBLE
                    imgStar4.visibility = View.VISIBLE
                }
                5 -> {
                    imgStar1.visibility = View.VISIBLE
                    imgStar2.visibility = View.VISIBLE
                    imgStar3.visibility = View.VISIBLE
                    imgStar4.visibility = View.VISIBLE
                    imgStar5.visibility = View.VISIBLE
                }
            }

//            textViewDifficulty.text= "난이도 ${position+1}"
        }


    }


    override fun getItemCount(): Int {
        return items.size
    }


}