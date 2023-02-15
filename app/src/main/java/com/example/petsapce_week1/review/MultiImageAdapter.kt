package com.example.petsapce_week1.review

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsapce_week1.R

class MultiImageAdapter(val items: ArrayList<Uri>, val context: Context) : RecyclerView.Adapter<MultiImageAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) :RecyclerView.ViewHolder(v){
        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.image)

        fun bind(listener: View.OnClickListener, item: String) {
            view.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.multi_image_review, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item)
            .override(80, 80)
            .into(holder.image)
        Log.d("숙소 position", "$position")
        Log.d("숙소 item0", "${items[0]}")
    }


    override fun getItemCount(): Int {
        return items.size
    }
}