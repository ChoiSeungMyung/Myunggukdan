package com.makeus.android.myunggukdan.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WastedItem(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val imageResourceId: Int,
    val createdDate: String
)