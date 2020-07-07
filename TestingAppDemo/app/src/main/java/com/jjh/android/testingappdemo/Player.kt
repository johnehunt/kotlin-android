package com.jjh.android.testingappdemo

open class Player(val counter: Counter) {

    override fun toString(): String {
        val sb = StringBuilder("Player(")
        sb.append(counter)
        sb.append(')')
        return sb.toString()
    }

    open val isAutomatedPlayer: Boolean
        get() = false

}