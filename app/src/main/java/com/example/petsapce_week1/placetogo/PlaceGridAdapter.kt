package com.example.petsapce_week1.placetogo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.petsapce_week1.R
import kotlinx.android.synthetic.main.placetogo_grid_itemlist.view.*


class PlaceGridAdapter(val context: Context, var img_list: Array<Int>) : BaseAdapter() {
    override fun getCount(): Int {
        return img_list.size
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.placetogo_grid_itemlist, null)

        view.menu_seoul.setImageResource(img_list[position])

        return view
    }


}