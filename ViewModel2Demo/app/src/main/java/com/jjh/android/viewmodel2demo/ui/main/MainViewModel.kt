package com.jjh.android.viewmodel2demo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val sterlingToDollarRate = 1.28
    }

    var result: MutableLiveData<Double> = MutableLiveData()
        private set

    var amount: String
        get() = result.toString()
        set(value) {
            result.value = value.toDouble() * sterlingToDollarRate
        }

}