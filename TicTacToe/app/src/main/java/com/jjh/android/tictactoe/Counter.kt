package com.jjh.android.tictactoe

/**
 * Represents a Counter used on the board
 */
class Counter private constructor(private val label: String) {

    companion object {
        // Set up X and O Counter
        val X = Counter("X")
        val O = Counter("O")
    }

    override fun toString() = label

}