package com.jjh.android.viewmodeldemo.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
        private const val sterlingToDollarRate = 1.28
    }

    private var sterlingText = ""
    private var result: Double = 0.0

    fun setAmount(value: String) {
        Log.d(TAG, "setAmount")
        sterlingText = value
        result = value.toDouble() * sterlingToDollarRate
    }

    fun getResult(): Double {
        Log.d(TAG, "setAmount")
        return result
    }

}