package com.makeus.android.myunggukdan.extension

import android.widget.TextView
import androidx.annotation.ColorRes

internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))