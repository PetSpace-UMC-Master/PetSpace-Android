package com.example.petsapce_week1.home.calendar

import android.graphics.Color
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class SundayDecorator : DayViewDecorator {
    private val sundayColor = Paint().apply {
        color = Color.parseColor("#BB2649")
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        val calendar = Calendar.getInstance()
        calendar.set(day.year, day.month, day.day)
        return  calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(object:ForegroundColorSpan(sundayColor.color){})
    }
}