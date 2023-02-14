package com.example.petsapce_week1.home.calendar

import android.annotation.SuppressLint
import android.content.Context
import com.example.petsapce_week1.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


class DayDecorator(context: Context?):DayViewDecorator {
    private var date = CalendarDay.today()
    @SuppressLint("UseCompatLoadingForDrawables")
    val drawable = context?.resources?.getDrawable(R.drawable.calendar_selector)
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {

            view?.setBackgroundDrawable(drawable!!)

    }
}
