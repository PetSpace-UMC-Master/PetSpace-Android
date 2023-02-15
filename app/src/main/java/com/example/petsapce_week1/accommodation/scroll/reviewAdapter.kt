package com.example.petsapce_week1.accommodation.scroll

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.petsapce_week1.databinding.AccReviewRowBinding

class reviewAdapter(val items: ArrayList<reviewData>, val context: Context) : RecyclerView.Adapter<reviewAdapter.ViewHolder>() {

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
        fun bind(imageUrl: String){
            val requestOptions = RequestOptions().transform(CircleCrop())
            Glide.with(itemView)
                .load(imageUrl)
                .apply(requestOptions)
                .into(binding.imgFace)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AccReviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            val imageUrl = items[position].img
            bind(imageUrl)
            binding.imgFace.scaleType = ImageView.ScaleType.CENTER_CROP

            binding.textName.text = items[position].name
            binding.textDate.text = items[position].date
            binding.textDetail.text = items[position].text
            when (items[position].star) {
                1 -> {
                    binding.imgStar1.visibility = View.VISIBLE
                }
                2 -> {
                    binding.imgStar1.visibility = View.VISIBLE
                    binding.imgStar2.visibility = View.VISIBLE
                }
                3 -> {
                    binding.imgStar1.visibility = View.VISIBLE
                    binding.imgStar2.visibility = View.VISIBLE
                    binding.imgStar3.visibility = View.VISIBLE
                }
                4 -> {
                    binding.imgStar1.visibility = View.VISIBLE
                    binding.imgStar2.visibility = View.VISIBLE
                    binding.imgStar3.visibility = View.VISIBLE
                    binding.imgStar4.visibility = View.VISIBLE
                }
                5 -> {
                    binding.imgStar1.visibility = View.VISIBLE
                    binding.imgStar2.visibility = View.VISIBLE
                    binding.imgStar3.visibility = View.VISIBLE
                    binding.imgStar4.visibility = View.VISIBLE
                    binding.imgStar5.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}