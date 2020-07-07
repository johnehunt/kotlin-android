package com.jjh.android.testingappdemo

class Calculator {
    private var _count = 0
    val count: Int
        get() = _count

    fun increment() {
        _count++
    }

    fun decrement() {
        _count--
    }

}