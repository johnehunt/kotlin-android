package com.jjh.android.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var board: Board

    init {
        Log.d(TAG, "init()")
        board = Board()
    }

    fun onButtonClick(view: View) {
        Log.d(TAG, "onButtonClick()")
        val buttonClicked = view as Button
        val buttonText = buttonClicked.text.toString()
        if (buttonText != " ") {
            Toast.makeText(this,
                "Cell is already in use!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val player = board.humanPlayer
            var finished = checkGameStatus(player, buttonClicked)
            if (finished) {
                restartButton.isEnabled = true
            } else {
                val buttonSelected = makeComputerMove()
                finished = checkGameStatus(board.computerPlayer, buttonSelected)
                if (finished) {
                    restartButton.isEnabled = true
                }
            }
        }
    }

    fun onRestartButtonClick(v: View) {
        Log.d(TAG, "onRestartButtonClick")
        setupNewBoard()
        button0.text = " "
        button1.text = " "
        button2.text = " "
        button3.text = " "
        button4.text = " "
        button5.text = " "
        button6.text = " "
        button7.text = " "
        button8.text = " "
        restartButton.isEnabled = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Disable restart
        restartButton.isEnabled = false
    }

    private fun setupNewBoard() {
        Log.d(TAG, "setupNewBoard()")
        board = Board()
        if (board.firstPlayer.isAutomatedPlayer) {
            makeComputerMove()
        }
    }

    private fun makeComputerMove(): Button {
        Log.d(TAG, "makeComputerMove()")
        val move = board.computerPlayerMakeMove()
        val tag = move.x.toString() + "," + move.y
        val buttonSelected = mainLayout.findViewWithTag<Button>(tag)
        buttonSelected.text = move.counter.toString()
        return buttonSelected
    }

    private fun checkGameStatus(player: Player, buttonClicked: Button): Boolean {
        Log.d(TAG, "checkGameStatus()")
        val counter = player.counter
        val move = Move(getButtonRow(buttonClicked),
                        getButtonCol(buttonClicked),
                        counter)
        board.addMove(move)
        buttonClicked.text = counter.toString()
        return if (board.checkForWinner(player)) {
            showWinnerMessage(player)
            true
        } else if (board.isFull) {
            showTieMessage()
            true
        } else {
            false
        }
    }

    private fun showMessage(message: String) {
        Log.d(TAG, "showMessage()")
        Toast.makeText(this,
            message,
            Toast.LENGTH_SHORT).show()
    }

    private fun showWinnerMessage(player: Player) {
        Log.d(TAG, "showWinnerMessage()")
        showMessage("Well Done $player WON!!")
    }

    private fun showTieMessage() {
        Log.d(TAG, "showWinnerMessage()")
        showMessage("The Game was a Tie!!")
    }

    private fun getButtonRow(button: Button): Int {
        val tagString = button.tag as String
        val rowString = tagString.substring(0, 1)
        return rowString.toInt()
    }

    private fun getButtonCol(button: Button): Int {
        val tagString = button.tag as String
        val colString = tagString.substring(2, 3)
        return colString.toInt()
    }

}