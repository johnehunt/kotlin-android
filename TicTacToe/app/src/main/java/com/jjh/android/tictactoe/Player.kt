package com.jjh.android.tictactoe

open class Player(val counter: Counter) {

    override fun toString()= "Player($counter)"

    open val isAutomatedPlayer = false

}