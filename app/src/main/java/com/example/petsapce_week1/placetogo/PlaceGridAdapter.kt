package com.example.petsapce_week1.placetogo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import com.example.petsapce_week1.R
import com.example.petsapce_week1.accommodation.AccMainActivity
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

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.placetogo_grid_itemlist, null)

        view.menu_seoul.setImageResource(img_list[position])

        val button = view.findViewById<ImageButton>(R.id.menu_seoul)
        button.setOnClickListener {
            val intent = Intent(context, AccMainActivity::class.java)
            context.startActivity(intent)

//      프래그먼트로 이동 시
//            val newFragment = NewFragment.newInstance(getItem(position) as String)
//            fragment.parentFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_container, newFragment)
//                .addToBackStack(null)
//                .commit()
        }

        return view
    }


}