package com.jjh.android.tictactoe

open class Player(val counter: Counter) {

    override fun toString(): String {
        return "Player($counter)"
    }

    open fun isAutomatedPlayer(): Boolean {
        return false
    }
}