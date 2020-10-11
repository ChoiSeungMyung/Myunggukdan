package com.makeus.android.myunggukdan.data.room

import androidx.room.*
import com.makeus.android.myunggukdan.data.model.WastedItem

@Dao
interface WasteItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWasteItem(item: WastedItem)

    @Delete
    suspend fun deleteWasteItem(vararg items: WastedItem)

//    @Query("SELECT * FROM $DB_WASTE_ITEM")
//    suspend fun getWasteItemList(): LiveData<List<WastedItem>>

//    @Query("SELECT * FROM $DB_WASTE_ITEM")
//    suspend fun getWasteItem(id: Int): WastedItem
}