package com.jjh.android.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.jjh.android.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private var board: Board

    init {
        Log.d(TAG, "init()")
        board = Board()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Disable restart
        binding.restartButton.isEnabled = false
    }

    fun onButtonClick(view: View) {
        Log.d(TAG, "onButtonClick()")
        val buttonClicked = view as Button
        val buttonText = buttonClicked.text.toString()
        if (buttonText != " ") {
            showMessage("Cell is already in use!")
        } else {
            val player = board.humanPlayer
            var finished = checkGameStatus(player, buttonClicked)
            if (finished) {
                binding.restartButton.isEnabled = true
            } else {
                val buttonSelected = makeComputerMove()
                finished = checkGameStatus(board.computerPlayer, buttonSelected)
                if (finished) {
                    binding.restartButton.isEnabled = true
                }
            }
        }
    }

    fun onRestartButtonClick(v: View) {
        Log.d(TAG, "onRestartButtonClick")
        setupNewBoard()
        binding.button0.text = " "
        binding.button1.text = " "
        binding.button2.text = " "
        binding.button3.text = " "
        binding.button4.text = " "
        binding.button5.text = " "
        binding.button6.text = " "
        binding.button7.text = " "
        binding.button8.text = " "
        binding.restartButton.isEnabled = false
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
        val buttonSelected = binding.mainLayout.findViewWithTag<Button>(tag)
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