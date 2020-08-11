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
        private val TAG = "MainActivity"
    }

    private var board: Board

    init {
        Log.d(TAG, "init()")
        board = Board()
    }

    inner class ButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            Log.d(TAG,"ButtonHandler.onClick()")
            val buttonClicked = view as Button
            val buttonText = buttonClicked.text.toString()
            if (buttonText != " ") {
                Toast.makeText(this@MainActivity, "Cell is already in use!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val player: Player = board.humanPlayer
                var finished: Boolean = checkGameStatus(player, buttonClicked)
                if (finished) {
                    restartButton.isEnabled = true
                } else {
                    val buttonSelected: Button = makeComputerMove()
                    finished = checkGameStatus(board.computerPlayer, buttonSelected)
                    if (finished) {
                        buttonSelected.isEnabled = true
                    }
                }
            }
        }
    }

    inner class RestartButtonHandler : View.OnClickListener {
        override fun onClick(v: View) {
            Log.d(TAG, "RestartButtonHandler.onClick()")
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = ButtonHandler()

        // Setup buttons - all buttons use the same handler
        button0.setOnClickListener(handler)
        button1.setOnClickListener(handler)
        button2.setOnClickListener(handler)
        button3.setOnClickListener(handler)
        button4.setOnClickListener(handler)
        button5.setOnClickListener(handler)
        button6.setOnClickListener(handler)
        button7.setOnClickListener(handler)
        button8.setOnClickListener(handler)

        restartButton.setOnClickListener(RestartButtonHandler())
        // Disable restart
        restartButton.isEnabled = false

    }

    private fun setupNewBoard() {
        Log.d(TAG, "setupNewBoard()")
        board = Board()
        if (board.firstPlayer.isAutomatedPlayer()) {
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
        Log.d(TAG, "showWinnerMessage()")
        Toast.makeText(this, "The Game was a Tie!!", Toast.LENGTH_LONG)
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

    fun getBoard(): Board? {
        return board
    }
}