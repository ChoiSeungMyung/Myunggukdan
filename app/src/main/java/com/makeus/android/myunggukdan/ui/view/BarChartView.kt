package com.makeus.android.myunggukdan.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.makeus.android.myunggukdan.R
import com.makeus.android.myunggukdan.databinding.LayoutBarchartBinding
import com.makeus.android.myunggukdan.extension.loge

class BarChartView(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private val binding: LayoutBarchartBinding

    //    private val weeks = arrayOf("Mon, Tue, Web, Thr, Fri, Sat, Sun")
    private val weeks = arrayOf("월, 화, 수, 목, 금, 토, 일")
    private val randomValue = listOf(
        BarEntry(0f, getRandom()),
        BarEntry(1f, getRandom()),
        BarEntry(2f, getRandom()),
        BarEntry(3f, getRandom()),
        BarEntry(4f, getRandom()),
        BarEntry(5f, getRandom()),
        BarEntry(6f, getRandom())
    )

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_barchart, this, false)
        binding.barchart.apply {
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
                    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                        return weeks[value.toInt() % weeks.size]
                    }
                }
            }

            axisLeft.setDrawGridLines(false)

            data = BarData(
                emptyList()
            )
        }
    }

    fun setValue() {
        binding.barchart.apply {
            when (data.dataSetCount > 0) {
                true -> {
                    (data.getDataSetByIndex(0) as BarDataSet).run {
                        values = randomValue
                    }
                    data.apply {
                        notifyDataChanged()
                        notifyDataSetChanged()
                    }
                }

                false -> {
                    val data = BarData(
                        listOf(
                            BarDataSet(randomValue, "Test DataSet").apply {
                                setColors(Color.rgb(181, 181, 181))
                                barShadowColor = Color.rgb(239, 239, 239)
                                setDrawValues(false)
                            })
                    )
                    setData(data)
                    setFitBars(true)
                }
            }
            invalidate()
            drawChart()
        }
    }

    private fun drawChart() {
        randomValue.forEach {
            loge("$it")
        }
        binding.barchart.apply {
            (data.getDataSetByIndex(0) as BarDataSet).run {
                values = randomValue
            }
            data.dataSets.forEach {
                loge("$it")
            }
            data.notifyDataChanged()
            notifyDataSetChanged()
        }
    }

    private fun getRandom() =
        (((Math.random() * 100_000) + 10_000) / 1).toFloat()
}