package com.jjh.android.testingappdemo

import android.util.Log
import java.util.*

class ComputerPlayer(counter: Counter?, private val board: Board) :
    Player(counter!!) {
    private val random = Random()
    private fun randomlySelectMove(): Move {
        Log.d(this.javaClass.simpleName, "randomlySelectMove()")
        // Use a simplistic random selection approach
        // to find a cell to fill.
        var move: Move? = null
        while (move == null) {
            // Randomly select the cell
            val row = random.nextInt(2)
            val column = random.nextInt(2)
            Log.d(
                this.javaClass.simpleName,
                "randomlySelectMove() - $row, $column"
            )
            // Check to see if the cell is empty
            if (board.isCellEmpty(row, column)) {
                move = Move(row, column, counter)
            }
        }
        return move
    }// Choose the top right// Choose the top right// Choose the bottom right// Choose the top left// Choose the center

    // Provide a very simple algorithm for selecting a move
    val move: Move
        get() {
            Log.d(this.javaClass.simpleName, "getMove()")
            // Provide a very simple algorithm for selecting a move
            return if (board.isCellEmpty(1, 1)) {
                // Choose the center
                Move(1, 1, counter)
            } else if (board.isCellEmpty(0, 0)) {
                // Choose the top left
                Move(0, 0, counter)
            } else if (board.isCellEmpty(2, 2)) {
                // Choose the bottom right
                Move(2, 2, counter)
            } else if (board.isCellEmpty(0, 2)) {
                // Choose the top right
                Move(0, 2, counter)
            } else if (board.isCellEmpty(0, 2)) {
                // Choose the top right
                Move(2, 0, counter)
            } else {
                randomlySelectMove()
            }
        }

    override val isAutomatedPlayer: Boolean
        get() = true

}