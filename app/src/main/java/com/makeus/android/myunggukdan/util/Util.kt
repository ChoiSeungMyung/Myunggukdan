package com.makeus.android.myunggukdan.util

fun getRandomWasteful(): Float {
    val random = (((Math.random() * 90_000) + 10_000) / 1).toInt()
    return (random - (random % 10)).toFloat()
}
