package com.example.petsapce_week1.home.homefragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.AccMainActivity
import com.example.petsapce_week1.databinding.HomeMainRowBinding
import com.example.petsapce_week1.databinding.HomeMainRowChildBinding
import com.example.petsapce_week1.vo.HomeResponse
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import kotlinx.android.synthetic.main.activity_acc_main.view.*
import kotlinx.android.synthetic.main.home_main_row.view.*
import java.text.DecimalFormat

class HomeMainAdapter(var items: ArrayList<HomeMainData>) :
    RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: HomeMainData)
//        fun onClick(v: View, position: Int)
    }


    var itemClickListener: OnItemClickListener? = null //초기값 null값


    inner class ViewHolder(val binding: HomeMainRowBinding) :
        RecyclerView.ViewHolder(binding.root) {


        //        private val childRecyclerView: RecyclerView = binding.childRecyclerView
//        private val childViewPager: ViewPager = binding.childViewPager

     /*   init {
            *//* childRecyclerView.layoutManager = LinearLayoutManager(
                 itemView.context,
                 LinearLayoutManager.HORIZONTAL,
                 false
             )*//*
            binding.root.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition]) //?는 null일 수 도 있다고 알려주는 역할


                Log.d("touch3", adapterPosition.toString())
            }

        }*/
        @SuppressLint("SetTextI18n")
        fun bind(data: HomeMainData) {
            val cut = String.format("%.2f", data.score)
            val priceCut = DecimalFormat("#,###")
            var price = priceCut.format(data.price)

            val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = HomeChildAdapter(data.imgList)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)


            binding.apply {
                textPrice.text = "₩${price} / 박"
                textDate.text = data.date.toString()
                textLoc.text = data.location
                textScore.text = cut
                textReview.text = "("+data.review.toString()+")"

            }
      /*      val springDotsIndicator = binding.dotsIndicator
            val viewPager = binding.childViewPager
            val adapter = HomeChildAdapter(items[position].imgList)
            viewPager.adapter = adapter
            springDotsIndicator.attachTo(viewPager)*/

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HomeMainRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        val roomIDNext = items[position].roomID

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context,AccMainActivity::class.java)
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



