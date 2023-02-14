package com.example.petsapce_week1.home.calendar

import android.graphics.Color
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class SaturdayDecorator : DayViewDecorator {
    private val saturdayColor = Paint().apply {
        color = Color.parseColor("#2662BB")
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        val calendar = Calendar.getInstance()
        calendar.set(day.year, day.month, day.day)
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(saturdayColor.color))
    }
}
