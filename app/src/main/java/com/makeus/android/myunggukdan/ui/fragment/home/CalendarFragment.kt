package com.makeus.android.myunggukdan.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragHomeCalendarBinding
import com.makeus.android.myunggukdan.databinding.LayoutCalenderDayBinding
import com.makeus.android.myunggukdan.databinding.LayoutCalenderHeaderBinding
import com.makeus.android.myunggukdan.extension.setTextColorRes
import com.makeus.android.myunggukdan.extension.toPrice
import com.makeus.android.myunggukdan.util.daysOfWeekFromLocale
import kotlinx.android.synthetic.main.frag_home_calendar.view.*
import java.time.LocalDate
import java.time.YearMonth

/**
 * TODO: Calendar 부분 리팩터 필요
 */
class CalendarFragment : Fragment() {
    private lateinit var binding: FragHomeCalendarBinding
    private var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay
            val binding = LayoutCalenderDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    when (day.owner == DayOwner.THIS_MONTH) {
                        true -> {
                            when (selectedDate != day.date) {
                                true -> {
                                    val oldDate = selectedDate
                                    selectedDate = day.date
                                    Toast.makeText(requireContext(), "$selectedDate", Toast.LENGTH_SHORT).show()
                                    val binding = this@CalendarFragment.binding
                                    oldDate?.let {
                                        binding.fragHomeCalendarCalendar.notifyDateChanged(it)
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = LayoutCalenderHeaderBinding.bind(view).root
        }

        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        binding.apply {
            val testTotalMoney = 263_000
            fragHomeCalendarMonthWrapper.frag_home_calendar_month_total.text =
                String.format(resources.getString(R.string.money_unit), testTotalMoney.toPrice())

            fragHomeCalendarCalendar.setup(
                currentMonth.minusMonths(12),
                currentMonth.plusMonths(3),
                daysOfWeek.first()
            )
            fragHomeCalendarCalendar.scrollToMonth(currentMonth)
            fragHomeCalendarCalendar.dayBinder = object : DayBinder<DayViewContainer> {
                override fun create(view: View): DayViewContainer = DayViewContainer(view)
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    val layout = container.binding.layoutCalenderDayRoot
                    container.day = day
                    container.binding.itemCalenderDay.text = day.date.dayOfMonth.toString()

                    when (day.owner == DayOwner.THIS_MONTH) {
                        true -> { // 해당월에 해당하는 날짜일때
                            container.binding.itemCalenderDay.setTextColorRes(R.color.colorBlack100)
                        }
                        false -> { // 해당월에 해당하는 날짜가 아닐때
                            container.binding.itemCalenderDay.setTextColorRes(R.color.colorGreyVeryLight)
                        }
                    }
                }
            }

            fragHomeCalendarCalendar.monthScrollListener = {
                fragHomeCalendarMonthWrapper.frag_home_calendar_month.text = String.format(
                    resources.getString(R.string.frag_home_calendar_month),
                    it.year,
                    it.month
                )
            }

            fragHomeCalendarCalendar.monthHeaderBinder =
                object : MonthHeaderFooterBinder<MonthViewContainer> {
                    override fun create(view: View): MonthViewContainer = MonthViewContainer(view)
                    override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                        if (container.legendLayout.tag == null) {
                            container.legendLayout.tag = month.yearMonth
                            month.yearMonth
                        }
                    }
                }
        }
    }

    companion object {
        private var instance: CalendarFragment? = null

        fun getInstance() = instance
            ?: synchronized(this) {
                instance
                    ?: CalendarFragment().also { instance = it }
            }

        fun newInstance() = CalendarFragment()
    }
}