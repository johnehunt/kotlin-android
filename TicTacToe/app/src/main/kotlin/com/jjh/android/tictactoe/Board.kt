package com.jjh.android.tictactoe

import android.util.Log
import java.util.*

class Board {

    private val cells =
        Array(3) { arrayOfNulls<Counter>(3) }

    val firstPlayer: Player
    val humanPlayer: Player
    val computerPlayer: ComputerPlayer

    init {
        Log.d(this.javaClass.simpleName, "constructor()")
        // Randomly allocate user to X or O
        val rand = Random()
        if (rand.nextInt(100) > 49) {
            humanPlayer = Player(Counter.X)
            computerPlayer = ComputerPlayer(Counter.O, this)
            firstPlayer = humanPlayer
        } else {
            computerPlayer = ComputerPlayer(Counter.O, this)
            humanPlayer = Player(Counter.X)
            firstPlayer = computerPlayer
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

    private fun isCellEmpty(counter: Counter?) = counter == null

    fun isCellEmpty(row: Int, col: Int) = cells[row][col] == null

    private fun cellContains(
        counter: Counter,
        row: Int,
        column: Int) =  cells[row][column] == counter

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