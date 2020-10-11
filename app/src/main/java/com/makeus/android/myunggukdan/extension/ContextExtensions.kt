package com.makeus.android.myunggukdan.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

internal fun Context.makeToast(msg: String, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, msg, length).show()