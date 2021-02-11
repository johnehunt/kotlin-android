package com.jjh.android.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val sterlingToDollarRate = 1.38
    }

    var result: Double = 0.0
       private set

    var amount: String
        get() = result.toString()
        set(value) {
            result = value.toDouble() * sterlingToDollarRate
        }

}