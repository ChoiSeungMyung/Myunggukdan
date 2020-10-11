package com.makeus.android.myunggukdan.data.model

import androidx.databinding.ObservableBoolean

data class WasteItem(
    val name: String,
    val icon: Int,
    val isSelected: ObservableBoolean = ObservableBoolean(false)
)