package com.makeus.android.myunggukdan.util

import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.*

fun getRandomWasteful(): Float {
    val random = (((Math.random() * 90_000) + 10_000) / 1).toInt()
    return (random - (random % 10)).toFloat()
}

fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    var daysOfWeek = DayOfWeek.values()
    if (firstDayOfWeek != DayOfWeek.MONDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}