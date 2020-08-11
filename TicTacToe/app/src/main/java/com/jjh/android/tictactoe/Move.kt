package com.jjh.android.tictactoe

class Move(val x: Int, val y: Int, val counter: Counter) {

    override fun toString(): String {
        return "Move($x, $y: $counter)"
    }

}