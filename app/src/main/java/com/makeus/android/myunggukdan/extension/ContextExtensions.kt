package com.makeus.android.myunggukdan.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)