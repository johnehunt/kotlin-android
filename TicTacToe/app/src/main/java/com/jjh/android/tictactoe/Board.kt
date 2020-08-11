package com.jjh.android.tictactoe

import android.util.Log
import java.util.*

class Board {

    private val cells =
        Array(3) { arrayOfNulls<Counter>(3) }

    var firstPlayer: Player
    var humanPlayer: Player
    var computerPlayer: ComputerPlayer

    init {
        Log.d(this.javaClass.simpleName, "constructor()")
        // Randomly allocate user to X or O
        val rand = Random()
        val r = rand.nextInt(100)
        if (r > 49) {
            val playerOne = Player(Counter.X)
            humanPlayer = playerOne
            computerPlayer = ComputerPlayer(Counter.O, this)
            firstPlayer = playerOne
        } else {
            val playerOne = ComputerPlayer(Counter.O, this)
            computerPlayer = playerOne
            humanPlayer = Player(Counter.X)
            firstPlayer = playerOne
        }
    }

    fun computerPlayerMakeMove(): Move {
        val move = computerPlayer.move
        addMove(move)
        return move
    }

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
               // Across the top
        return cellContains(c, 0, 0) && cellContains(c, 0, 1) && cellContains(c, 0, 2) ||
                // Across the middle
                cellContains(c, 1, 0) && cellContains(c, 1, 1) && cellContains(c, 1, 2) ||
                // Across the bottom
                cellContains(c, 2, 0) && cellContains(c, 2, 1) && cellContains(c, 2, 2) ||
                // down the left side
                cellContains(c, 0, 0) && cellContains(c, 1, 0) && cellContains(c, 2, 0) ||
                // down the middle
                cellContains(c, 0, 1) && cellContains(c, 1, 1) && cellContains(c, 2, 1) ||
                // down the right side
                cellContains(c, 0, 2) && cellContains(c, 1, 2) && cellContains(c, 2, 2) ||
                // diagonal
                cellContains(c, 0, 0) && cellContains(c, 1, 1) && cellContains(c, 2, 2) ||
                // other diagonal
                cellContains(c, 0, 2) && cellContains(c, 1, 1) && cellContains(c, 2, 0)
    }


}