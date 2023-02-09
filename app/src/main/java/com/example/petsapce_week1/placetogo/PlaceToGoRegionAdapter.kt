package com.example.petsapce_week1.placetogo

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.PlacetogoItemsBinding
import com.example.petsapce_week1.vo.FavoriteBackendResponse
import com.example.petsapce_week1.vo.FavoriteData
import java.text.DecimalFormat

class PlaceToGoRegionAdapter(var items: MutableList<FavoriteBackendResponse.Favorite>) : RecyclerView.Adapter<PlaceToGoRegionAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: FavoriteData)
//        fun onClick(v: View, position: Int)
    }
    var itemClickListener: OnItemClickListener? = null //초기값 null값

    inner class ViewHolder(val binding: PlacetogoItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: FavoriteBackendResponse.Favorite) {
            Log.d("함께 갈 곳 data", data.toString())
            //val cut = String.format("%.2f", data.averageReviewScore)
            val priceCut = DecimalFormat("#,###")
            var price = priceCut.format(data.price)
            Log.d("함께 갈 곳 data", data.toString())
            val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = PlaceToGoChildAdapter(data.roomImages)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)
            Log.d("함께 갈 곳 data", data.toString())

            binding.apply {
                textPrice.text = "₩${price} / 박"
                textLoc.text = data.roomAddress
                textScore.text = data.averageReviewScore.toString()
            }
            /*      val springDotsIndicator = binding.dotsIndicator
                  val viewPager = binding.childViewPager
                  val adapter = HomeChildAdapter(items[position].imgList)
                  viewPager.adapter = adapter
                  springDotsIndicator.attachTo(viewPager)*/

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("함께 갈 곳 items", "$items")
        val binding =
            PlacetogoItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //child recyclerview
//        holder.binding.childRecyclerView.adapter = HomeChildAdapter(items[position].imgList)

        /*   val restadapter = HomeChildAdapter(items[position].imgList)
           holder.binding.childViewPager.adapter = restadapter

           holder.binding.childViewPager.adapter = HomeChildAdapter(items[position].imgList)*/

        /*   val springDotsIndicator = holder.binding.dotsIndicator
           val viewPager = holder.binding.childViewPager
           val adapter = HomeChildAdapter(items[position].imgList)
           viewPager.adapter = adapter
           springDotsIndicator.attachTo(viewPager)*/

        val roomIDNext = items[position].id

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AccMainActivity::class.java)
            intent.putExtra("content",roomIDNext)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Log.d("content",roomIDNext.toString())
        }


//        holder.childViewPager.adapter = HomeChildViewPagerAdapter(items[position].imgList)
//        holder.binding.childViewPager.visibility = View.VISIBLE
        holder.binding.apply {
            topcardview.cardElevation = 0f
            btnHeart.setOnClickListener {
                btnHeart.isSelected = btnHeart.isSelected != true
            }


            //이미지는 이런식으로 담아야함.
//            imgMain.setImageResource(items[position].img)
            /*  textLoc.text = items[position].location
              textScore.text = items[position].score.toString()
              textDate.text = items[position].date.toString()
              textPrice.text = "₩" + items[position].price.toString() + " / 박"*/
//            textViewDifficulty.text= "난이도 ${position+1}"

        }
        holder.bind(items[position])

    }


    override fun getItemCount(): Int {
        return items.size
    }

    /* fun updateItems(newItems: HomeResponse) {
         items.clear()
         items.addAll(newItems)
         notifyDataSetChanged()
     }*/


}

