package com.jjh.android.viewmodel2demo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
        private const val sterlingToDollarRate = 1.28
    }

    private var sterlingText = ""
    private var result: MutableLiveData<Double> = MutableLiveData()

    fun setAmount(value: String) {
        Log.d(TAG, "setAmount")
        sterlingText = value
        // result = value.toDouble() * sterlingToDollarRate
        result.setValue(value.toDouble() * sterlingToDollarRate)
    }

    fun getResult(): MutableLiveData<Double> {
        Log.d(TAG, "setAmount")
        return result
    }

}