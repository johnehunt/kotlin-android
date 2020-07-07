package com.jjh.android.viewmodeldemo

import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    // Tracks the counter value
    var count = 0

    fun increment() {
        count++
    }

}