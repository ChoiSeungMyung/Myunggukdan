package com.makeus.android.myunggukdan.data.model

import androidx.databinding.ObservableBoolean
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "waste_item")
data class WastedItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    var price: Int = 0,
    var description: String = title,
    var imageResourceId: Int,
    var createdDate: Long = System.currentTimeMillis()
) {
    @Ignore
    var isSelected: ObservableBoolean = ObservableBoolean(false)
}