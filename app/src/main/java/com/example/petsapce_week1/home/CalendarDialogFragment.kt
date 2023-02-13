package com.example.petsapce_week1.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.petsapce_week1.R.style.CalendarWidgetHeader
import com.example.petsapce_week1.databinding.FragmentCalendarDialogBinding
import com.example.petsapce_week1.home.calendar.SaturdayDecorator
import com.example.petsapce_week1.home.calendar.SundayDecorator
import com.prolificinteractive.materialcalendarview.*
import java.util.*


class CalendarDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCalendarDialogBinding
    private lateinit var calendarView: MaterialCalendarView
    private lateinit var calendar2: CalendarView
    lateinit var viewModel: ResViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarDialogBinding.inflate(layoutInflater)
        calendarView = binding.calendarview

        viewModel = ViewModelProvider(requireActivity()).get(ResViewModel::class.java)


        // Inflate the layout for this fragment
        initViews()
        initDataBinding()



        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initDataBinding() {

        //시작일 끝일
        viewModel.curStartDate.observe(viewLifecycleOwner) {
            binding.btnResult.text = "$it ~ ${viewModel.curEndDate.value}"

        }
        viewModel.curEndDate.observe(viewLifecycleOwner){
            binding.btnResult.text = "${viewModel.curStartDate.value} ~ $it"

        }

    }


    private fun initViews() = with(binding) {

        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
//        dialog?.setCancelable(false)

        // background를 투명하게 만듦
        // (중요) Dialog는 내부적으로 뒤에 흰 사각형 배경이 존재하므로, 배경을 투명하게 만들지 않으면
        // corner radius의 적용이 보이지 않는다.
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        calendar2.

        btnResult.setOnClickListener {
            dismiss()
        }

        calendarView.setHeaderTextAppearance(CalendarWidgetHeader)

        calendarView.setOnRangeSelectedListener { widget, dates ->
            val startDay = dates[0].date.toString()
            val endDay = dates[dates.size - 1].date.toString()
            viewModel.getStardDate(startDay)
            viewModel.getEndDate(endDay)
            Log.d("day", "시작일 : $startDay, 종료일 : $endDay")
        }


/*
        val stCalendarDay = CalendarDay(
            currentYear,
            currentMonth,
            currentDate
        )
        val enCalendarDay = CalendarDay(
            endTimeCalendar.get(Calendar.YEAR),
            endTimeCalendar.get(Calendar.MONTH),
            endTimeCalendar.get(Calendar.DATE)
        )
*/

        //달 변경
//        val sundayDecorator = SundayDecorator()
        val saturdayDecorator = SaturdayDecorator()
        val sundayDecorator = SundayDecorator()
        calendarView.addDecorator(sundayDecorator)
        /*  calendarView.setOnMonthChangedListener(object : OnMonthChangedListener {
              override fun onMonthChanged(widget: MaterialCalendarView, date: CalendarDay) {
                  calendarView.removeDecorator(sundayDecorator)
                  val updatedSundayDecorator = SundayDecorator()
                  calendarView.addDecorator(updatedSundayDecorator)
              }
          })*/


//        calendarView.addDecorators(sundayDecorator, saturdayDecorator)

    }


}