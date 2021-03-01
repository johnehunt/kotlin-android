package com.jjh.android.testingappdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    init {
        Log.d(TAG, "init{}")
    }

    var board: Board = Board()
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Disable restart
        restartButton.isEnabled = false

        // Make first move
        setupNewBoard()
    }

    private fun onButtonClick(v: View) {
            Log.d(TAG, "onButtonClick()")
            val buttonClicked = v as Button
            val buttonText = buttonClicked.text.toString()
            if (buttonText != " ") {
                Toast.makeText(this, "Cell is already in use!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val player = board.humanPlayer
                var finished = checkGameStatus(player, buttonClicked)
                if (finished) {
                    restartButton!!.isEnabled = true
                } else {
                    val buttonSelected = makeComputerMove()
                    finished = checkGameStatus(board.computerPlayer, buttonSelected)
                    if (finished) {
                        buttonSelected.isEnabled = true
                    }
                }
            }
    }

    private fun onRestartButtonClick(v: View) {
            Log.d(TAG, "onRestartButtonClick()")
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
            restartButton!!.isEnabled = false

    }

    private fun setupNewBoard() {
        Log.d(TAG, "setupNewBoard()")
        board = Board()
        if (board.firstPlayer!!.isAutomatedPlayer) {
            makeComputerMove()
        }
    }

    private fun makeComputerMove(): Button {
        Log.d(TAG, "makeComputerMove()")
        val cp = board.computerPlayer as ComputerPlayer
        val move = cp.move
        board.addMove(move)
        val tag = move.x.toString() + "," + move.y
        val layout = findViewById<LinearLayout>(R.id.mainLayout)
        val buttonSelected =
            layout.findViewWithTag<Button>(tag)
        buttonSelected.text = move.counter.toString()
        return buttonSelected
    }

    private fun checkGameStatus(player: Player, buttonClicked: Button): Boolean {
        Log.d(TAG, "checkGameStatus()")
        val counter = player.counter
        val move = Move(getButtonRow(buttonClicked), getButtonCol(buttonClicked), counter)
        board.addMove(move)
        buttonClicked.text = counter.toString()
        var finished = false
        if (board.checkForWinner(player)) {
            showWinnerMessage(player)
            finished = true
        }
        if (!finished && board.isFull) {
            showTieMessage()
            finished = true
        }
        return finished
    }

    private fun showWinnerMessage(player: Player) {
        Log.d(TAG, "showWinnerMessage()")
        Toast.makeText(this, "Well Done $player WON!!", Toast.LENGTH_LONG)
            .show()
    }

    private fun showTieMessage() {
        Log.d(this.javaClass.simpleName, "showWinnerMessage()")
        Toast.makeText(this@MainActivity, "The Game was a Tie!!", Toast.LENGTH_LONG)
            .show()
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