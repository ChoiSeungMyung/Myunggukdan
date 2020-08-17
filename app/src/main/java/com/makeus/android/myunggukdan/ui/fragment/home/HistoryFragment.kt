package com.makeus.android.myunggukdan.ui.fragment.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.FragHomeHistoryBinding
import com.makeus.android.myunggukdan.util.ColorSet
import com.makeus.android.myunggukdan.util.getRandomWasteful
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class HistoryFragment : Fragment() {
    //    private val weeks = arrayOf("Mon", "Tue", "Web", "Thr", "Fri", "Sat", "Sun")
    private val weeks = arrayOf("월", "화", "수", "목", "금", "토", "일")
    private val randomValue = listOf(
        BarEntry(0f, getRandomWasteful()),
        BarEntry(1f, getRandomWasteful()),
        BarEntry(2f, getRandomWasteful()),
        BarEntry(3f, getRandomWasteful()),
        BarEntry(4f, getRandomWasteful()),
        BarEntry(5f, getRandomWasteful()),
        BarEntry(6f, getRandomWasteful())
    )

    private lateinit var binding: FragHomeHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            Glide.with(view)
                .load(R.drawable.test_2)
                .into(homeHistoryCharacter)

            homeHistoryWastefulText.text = "김낭비님, 오늘 하루"
            homeHistoryWastefulTotal.text = "15,000원"
            homeHistoryWastefulDescription.text = " 을 낭비했어요!"

            barchart.apply {
                description.isEnabled = false
                setMaxVisibleValueCount(60)
                setPinchZoom(false)
                setDrawBarShadow(true)
                setDrawGridBackground(false)
                animateY(1000)
                legend.isEnabled = false
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String =
                            weeks[value.toInt() % weeks.size]
                    }
                }
                axisRight.isEnabled = false
                axisLeft.isEnabled = false

                data = BarData(
                    emptyList()
                )

                barChartSetValue()

                setOnTouchListener { _, _ ->
                    true
                }
            }

            homeHistoryTodayWastefulDate.text =
                SimpleDateFormat("yyyy.MM.dd", Locale.US).format(Date())
        }
    }

    private fun barChartSetValue() {
        binding.barchart.apply {
            when (data.dataSetCount > 0) {
                false -> {
                    val barData = BarData(
                        listOf(
                            BarDataSet(randomValue, "Test DataSet").apply {
                                colors = ColorSet.chartColorSet
                                barShadowColor = Color.rgb(239, 239, 239)
                                setDrawValues(false)
                            })
                    ).apply {
                        barWidth = 0.6f
                    }

                    data = barData
                    setFitBars(true)

                    invalidate()
                    drawChart()
                }
            }
        }
    }

    private fun drawChart() {
        binding.apply {
            barchart.apply {
                (data.getDataSetByIndex(0) as BarDataSet).run {
                    values = randomValue
                }
                data.notifyDataChanged()
                notifyDataSetChanged()
            }

            val chartAverage = randomValue.map { it.y }.average().toInt()
            val gtChartAverage = randomValue.filter { it.y > chartAverage }.size

            homeHistoryChartGtAverageValue.text = "${gtChartAverage}회!"
            homeHistoryChartAverage.text =
                "평균 ${NumberFormat.getNumberInstance(Locale.US).format(chartAverage)}원"
        }
    }
}