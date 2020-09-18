package com.makeus.android.myunggukdan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.BarEntry
import com.makeus.android.myunggukdan.extension.loge
import com.makeus.android.myunggukdan.util.getRandomWasteful
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _addWasteItem: MutableLiveData<Boolean> = MutableLiveData(false)
    val addWastedItem: LiveData<Boolean> = _addWasteItem
    private val _wasteWeekItemList: MutableLiveData<List<BarEntry>> = MutableLiveData()
    val wasteWeekItemList: LiveData<List<BarEntry>> = _wasteWeekItemList

    fun getWasteWeekItem() {
        _wasteWeekItemList.postValue(
            listOf(
                BarEntry(0f, getRandomWasteful()),
                BarEntry(1f, getRandomWasteful()),
                BarEntry(2f, getRandomWasteful()),
                BarEntry(3f, getRandomWasteful()),
                BarEntry(4f, getRandomWasteful()),
                BarEntry(5f, getRandomWasteful()),
                BarEntry(6f, getRandomWasteful())
            )
        )
    }

    fun addWasteItem() {
        _addWasteItem.value?.let {
            _addWasteItem.postValue(!it)
        } ?: _addWasteItem.postValue(false)
    }
}