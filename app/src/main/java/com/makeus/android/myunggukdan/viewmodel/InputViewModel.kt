package com.makeus.android.myunggukdan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class InputViewModel(application: Application) : AndroidViewModel(application) {
    private val _amount: MutableLiveData<String> = MutableLiveData()
    val amount: LiveData<String> = _amount
    private var localAmount = ""
    init {
        _amount.postValue(0.toString())
    }
    fun setAmountSingle(input: String) {
        val result = StringBuilder(localAmount).append(input)
        localAmount = result.toString()
        _amount.postValue(localAmount)
    }

    fun setAmountInt(input: Int) {
        if (localAmount == "") localAmount = "0"
        localAmount = (localAmount.toInt()  + input).toString()
        _amount.postValue(localAmount)
    }

    fun dropAmountSingle() {
        localAmount = localAmount.substring(0, localAmount.length - 1)
        _amount.postValue(localAmount)
    }

    fun initAmount() {
        localAmount = ""
        _amount.postValue(localAmount)
    }
}