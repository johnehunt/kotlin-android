package com.jjh.android.testingappdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    init {
        Log.d(this.javaClass.simpleName, "constructor()")
    }

    var board: Board = Board()
        private set

    private var restartButton: Button? = null

    internal inner class ButtonHandler : View.OnClickListener {
        override fun onClick(v: View) {
            Log.d(
                this.javaClass.simpleName,
                "MainActivity.ButtonHandler.onClick()"
            )
            val buttonClicked = v as Button
            val buttonText = buttonClicked.text.toString()
            if (buttonText != " ") {
                Toast.makeText(this@MainActivity, "Cell is already in use!", Toast.LENGTH_SHORT)
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
    }

    internal inner class RestartButtonHandler : View.OnClickListener {
        override fun onClick(v: View) {
            Log.d(
                this.javaClass.simpleName,
                "MainActivity.RestartButtonHandler.onClick()"
            )
            setupNewBoard()
            var button = findViewById<Button>(R.id.button0)
            button.text = " "
            button = findViewById(R.id.button1)
            button.text = " "
            button = findViewById(R.id.button2)
            button.text = " "
            button = findViewById(R.id.button3)
            button.text = " "
            button = findViewById(R.id.button4)
            button.text = " "
            button = findViewById(R.id.button5)
            button.text = " "
            button = findViewById(R.id.button6)
            button.text = " "
            button = findViewById(R.id.button7)
            button.text = " "
            button = findViewById(R.id.button8)
            restartButton!!.isEnabled = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this.javaClass.simpleName, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = ButtonHandler()

        // Setup buttons - all buttons use the same handler
        var button = findViewById<Button>(R.id.button0)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button1)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button2)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button3)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button4)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button5)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button6)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button7)
        button.setOnClickListener(handler)
        button = findViewById(R.id.button8)
        button.setOnClickListener(handler)

        // Disable restart
        restartButton = findViewById(R.id.button9)
        restartButton!!.setOnClickListener(RestartButtonHandler())
        restartButton!!.setEnabled(false)

        // Make first move
        setupNewBoard()
    }

    private fun setupNewBoard() {
        Log.d(this.javaClass.simpleName, "setupNewBoard()")
        board = Board()
        if (board.firstPlayer!!.isAutomatedPlayer) {
            makeComputerMove()
        }
    }

    private fun makeComputerMove(): Button {
        Log.d(this.javaClass.simpleName, "makeComputerMove()")
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
        Log.d(this.javaClass.simpleName, "checkGameStatus()")
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
        Log.d(this.javaClass.simpleName, "showWinnerMessage()")
        Toast.makeText(this@MainActivity, "Well Done $player WON!!", Toast.LENGTH_LONG)
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