package com.jjh.android.tictactoe

import android.util.Log
import java.util.Random

class ComputerPlayer(counter: Counter, private val board: Board) : Player(counter) {

    companion object {
        private const val TAG = "ComputerPlayer"
    }

    // Set up random number generator
    private val random = Random()

    // automated player property
    override val isAutomatedPlayer = true

    private fun randomlySelectMove(): Move {
        Log.d(TAG, "randomlySelectMove()")
        // Try to use a simplistic random selection approach
        // to find a cell to fill; if don't find a cell in 6 goes
        // then just find next free cell
        var attemptCount = 0
        var move: Move? = null
        while (move == null && attemptCount < 6) {
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
            attemptCount++
        }
        // Random selection did not work - just find next empty cell
        if (move == null) {
            for (x in 0..2) {
                for (y in 0..2) {
                    if (board.isCellEmpty(x, y)) {
                        move = Move(x, y, counter)
                    }
                }
            }
        }
        return move!!
    }

    // Provide a very simple algorithm for selecting a move
    val move: Move
        get() {
            Log.d(TAG, "getMove()")
            // Provide a very simple algorithm for selecting a move
            return when {
                board.isCellEmpty(1, 1) -> {
                    // Choose the center
                    Move(1, 1, counter)
                }
                board.isCellEmpty(0, 0) -> {
                    // Choose the top left
                    Move(0, 0, counter)
                }
                board.isCellEmpty(2, 2) -> {
                    // Choose the bottom right
                    Move(2, 2, counter)
                }
                board.isCellEmpty(0, 2) -> {
                    // Choose the top right
                    Move(0, 2, counter)
                }
                board.isCellEmpty(0, 2) -> {
                    // Choose the top right
                    Move(2, 0, counter)
                }
                else -> {
                    randomlySelectMove()
                }
            }
        }

}