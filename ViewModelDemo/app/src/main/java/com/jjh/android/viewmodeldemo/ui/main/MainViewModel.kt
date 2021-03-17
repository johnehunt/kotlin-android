package com.jjh.android.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModel

/**
 * ViewModel to use with MainFragment.
 * Hold data and functionality in the ViewModel.
 */
class MainViewModel : ViewModel() {

    companion object {
        private const val sterlingToDollarRate = 1.38
    }

    var dollarValue: Double = 0.0
       private set

    var sterlingValue: String
        get() = dollarValue.toString()
        set(value) {
            dollarValue = value.toDouble() * sterlingToDollarRate
        }

}