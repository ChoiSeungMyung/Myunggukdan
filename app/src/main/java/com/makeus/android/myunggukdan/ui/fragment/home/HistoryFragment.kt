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
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.extension.toPrice
import com.makeus.android.myunggukdan.ui.listener.AddWasteItemListener
import com.makeus.android.myunggukdan.util.ColorSet
import com.makeus.android.myunggukdan.util.getRandomWasteful
import com.makeus.android.myunggukdan.viewmodel.HistoryViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class HistoryFragment(private val historyViewModel: HistoryViewModel) : Fragment() {
    private val weeks = when(Locale.getDefault().displayLanguage.contains("한국")) {
        true -> arrayOf("월", "화", "수", "목", "금", "토", "일")
        false -> arrayOf("Mon", "Tue", "Web", "Thr", "Fri", "Sat", "Sun")
    }

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
            viewModel = historyViewModel
            Glide.with(view)
                .load(R.drawable.character_greycat_level_1)
                .into(homeHistoryCharacter)

            val testNickName = "김낭비"
            val testTotalText = 15_000
            homeHistoryWastefulText.text = String.format(resources.getString(R.string.frag_home_history_wasteful_text), testNickName)
            homeHistoryWastefulTotal.text = String.format(resources.getString(R.string.money_unit), testTotalText.toPrice())

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

                barChartSetValue(emptyList())

                setOnTouchListener { _, _ ->
                    true
                }
            }

            homeHistoryTodayWastefulDate.text =
                SimpleDateFormat("yyyy.MM.dd", Locale.US).format(Date())
        }

        historyViewModel.getWasteWeekItem()
        historyViewModel.wasteWeekItemList.observe(viewLifecycleOwner, {
            drawChart(it)
        })
    }

    private fun barChartSetValue(wasteWeekItemList: List<BarEntry>) {
        binding.barchart.apply {
            when (data.dataSetCount > 0) {
                false -> {
                    val barData = BarData(
                        listOf(
                            BarDataSet(wasteWeekItemList, "Test DataSet").apply {
                                colors = ColorSet.chartColorSet
                                barShadowColor = Color.rgb(249, 249, 249)
                                setDrawValues(false)
                            })
                    ).apply {
                        barWidth = 0.6f
                    }

                    data = barData
                    setFitBars(true)

                    invalidate()
                    drawChart(wasteWeekItemList)
                }
            }
        }
    }

    private fun drawChart(wasteWeekItemList: List<BarEntry>) {
        binding.apply {
            barchart.apply {
                (data.getDataSetByIndex(0) as BarDataSet).run {
                    values = wasteWeekItemList
                }
                data.notifyDataChanged()
                notifyDataSetChanged()
            }

            val chartAverage = wasteWeekItemList.map { it.y }.average().toInt()
            val gtChartAverage = wasteWeekItemList.filter { it.y > chartAverage }.size

            homeHistoryChartGtAverageValue.text = String.format(resources.getString(R.string.frag_home_history_chart_gt_average_value), gtChartAverage)
            homeHistoryChartAverage.text =
                String.format(resources.getString(R.string.frag_home_history_chart_average), chartAverage.toPrice())
        }
    }

    companion object {
            private var instance: HistoryFragment? = null

            fun getInstance(historyViewModel: HistoryViewModel) = instance
                ?: synchronized(this) {
                    instance
                        ?: HistoryFragment(historyViewModel).also { instance = it }
                }

            fun newInstance(historyViewModel: HistoryViewModel) = HistoryFragment(historyViewModel)
    }
}