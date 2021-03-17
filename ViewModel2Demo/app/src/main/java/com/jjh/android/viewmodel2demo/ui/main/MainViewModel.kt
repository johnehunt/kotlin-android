package com.jjh.android.viewmodel2demo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val sterlingToDollarRate = 1.28
    }

    var dollarValue: MutableLiveData<Double> = MutableLiveData()
        private set

    var sterlingValue: String
        get() = dollarValue.toString()
        set(value) {
            dollarValue.value = value.toDouble() * sterlingToDollarRate
        }

}