package com.jjh.android.viewmodel3demo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private const val sterlingToDollarRate = 1.28
    }

    var sterlingValue: MutableLiveData<String> = MutableLiveData()
    var dollarvalue: MutableLiveData<Double> = MutableLiveData()

    fun convertValue() {
        sterlingValue.apply {
            if (!this.value.equals("")) {
                dollarvalue.value =
                    value?.
                      toDouble()?.
                      times(sterlingToDollarRate)
            } else {
                dollarvalue.value = 0.0
            }
        }
    }

}