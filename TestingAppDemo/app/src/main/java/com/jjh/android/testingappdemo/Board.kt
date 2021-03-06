package com.jjh.android.testingappdemo

import android.util.Log
import java.util.*

class Board {
    private val cells =
        Array(
            3
        ) { arrayOfNulls<Counter>(3) }

    lateinit var firstPlayer: Player
    lateinit var humanPlayer: Player
    lateinit var computerPlayer: Player

    fun addMove(move: Move) {
        Log.d(this.javaClass.simpleName, "addMove($move)")
        val row = cells[move.x]
        row[move.y] = move.counter
    }

    fun isCellEmpty(counter: Counter?): Boolean {
        return counter == null
    }

    fun isCellEmpty(row: Int, col: Int): Boolean {
        val counter = cells[row][col]
        return counter == null
    }

    fun cellContains(
        counter: Counter,
        row: Int,
        column: Int
    ): Boolean {
        return cells[row][column] == counter
    }

    val isFull: Boolean
        get() {
            for (row in cells) {
                for (c in row) {
                    if (isCellEmpty(c)) {
                        return false
                    }
                }
            }
            return true
        }

    fun checkForWinner(player: Player): Boolean {
        Log.d(this.javaClass.simpleName, "checkForWinner($player)")
        val c = player.counter
        return cellContains(c, 0, 0) && cellContains(c, 0, 1) && cellContains(
            c,
            0,
            2
        ) ||  // Across the middle
                cellContains(c, 1, 0) && cellContains(c, 1, 1) && cellContains(
            c,
            1,
            2
        ) ||  // Across the bottom
                cellContains(c, 2, 0) && cellContains(c, 2, 1) && cellContains(
            c,
            2,
            2
        ) ||  // down the left side
                cellContains(c, 0, 0) && cellContains(c, 1, 0) && cellContains(
            c,
            2,
            0
        ) ||  // down the middle
                cellContains(c, 0, 1) && cellContains(c, 1, 1) && cellContains(
            c,
            2,
            1
        ) ||  // down the right side
                cellContains(c, 0, 2) && cellContains(c, 1, 2) && cellContains(
            c,
            2,
            2
        ) ||  // diagonal
                cellContains(c, 0, 0) && cellContains(c, 1, 1) && cellContains(
            c,
            2,
            2
        ) ||  // other diagonal
                cellContains(c, 0, 2) && cellContains(c, 1, 1) && cellContains(c, 2, 0)
    }

    init {
        Log.d(this.javaClass.simpleName, "constructor()")
        // Randomly allocate user to X or O
        val rand = Random()
        val r = rand.nextInt(100)
        if (r > 49) {
            firstPlayer =
                Player(Counter(Counter.X))
            humanPlayer = firstPlayer
            computerPlayer = ComputerPlayer(
                Counter(Counter.Y),
                this
            )
        } else {
            firstPlayer = ComputerPlayer(
                Counter(Counter.Y),
                this
            )
            computerPlayer = firstPlayer
            humanPlayer =
                Player(Counter(Counter.X))
        }
    }
}