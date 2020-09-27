package com.makeus.android.myunggukdan.extension

import java.text.DecimalFormat

fun Int.toPrice(): String {
    val pattern = "#,###"
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.groupingSize = 3

    return decimalFormat.format(this)
}