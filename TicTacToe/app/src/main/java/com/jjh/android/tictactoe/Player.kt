package com.jjh.android.tictactoe

open class Player(val counter: Counter) {

    override fun toString(): String {
        val sb = StringBuilder("Player(")
        sb.append(counter)
        sb.append(')')
        return sb.toString()
    }

    open fun isAutomatedPlayer(): Boolean {
        return false
    }
}