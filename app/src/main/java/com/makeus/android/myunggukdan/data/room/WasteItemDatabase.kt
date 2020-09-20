package com.makeus.android.myunggukdan.data.room

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.makeus.android.myunggukdan.data.DB_WASTE_ITEM
import com.makeus.android.myunggukdan.data.WastedItem

@Database(entities = [WastedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WasteItemDatabase : RoomDatabase() {
    abstract fun wasteItemDao(): WasteItemDao

    companion object {
        @Volatile
        private var instance: WasteItemDatabase? = null

        fun getInstance(context: Context): WasteItemDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also { instance = it }
            }
        }

        private fun buildDataBase(context: Context): WasteItemDatabase {
            return Room.databaseBuilder(context, WasteItemDatabase::class.java, DB_WASTE_ITEM)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

class Converters {
    @TypeConverter
    fun listToJson(value: List<String?>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<String?> {
        val objects = Gson().fromJson(value, Array<String?>::class.java)
        return objects.toList()
    }
}