package com.jjh.android.tictactoe


class Counter private constructor(private val label: String) {

    companion object {
        val X = Counter("X")
        val O = Counter("O")
    }

    override fun toString() = label

}