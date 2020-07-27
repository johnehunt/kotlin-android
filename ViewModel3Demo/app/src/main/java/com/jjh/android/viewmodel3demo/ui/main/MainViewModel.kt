package com.jjh.android.viewmodel3demo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
        private const val sterlingToDollarRate = 1.28
    }

    var dollarValue: MutableLiveData<String> = MutableLiveData()
    var result: MutableLiveData<Double> = MutableLiveData()

    fun convertValue() {
        dollarValue.apply {
            if (!this.value.equals("")) {
                result.value =
                    value?.
                      toDouble()?.
                      times(sterlingToDollarRate)
            } else {
                result.value = 0.0
            }
        }
    }

}