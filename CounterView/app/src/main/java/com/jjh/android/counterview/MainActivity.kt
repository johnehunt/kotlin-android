package com.jjh.android.counterview

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    // Instance variables to hold reference to counter
    // and text view (label) used to display the count
    private var text: TextView? = null
    private var count = 0

    // Inner classes to handle user button clicks
    internal inner class AddButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            count++
            text!!.text = count.toString() + ""
        }
    }

    internal inner class SubtractButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            count--
            text!!.text = count.toString() + ""
        }
    }

    internal inner class ToastButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            val toast = Toast.makeText(
                this@MainActivity,
                "Clicked $count",
                Toast.LENGTH_LONG
            )
            toast.setGravity(
                Gravity.CENTER,
                toast.xOffset / 2,
                toast.yOffset / 2
            )
            toast.show()
        }
    }

    internal inner class DatePickerButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            val datePickerListener =
                OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->

                    // when dialog box is closed, below method will be called.
                    val toast = Toast.makeText(
                        this@MainActivity,
                        "selected $selectedYear, $selectedMonth, $selectedDay",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                }
            val datePickerDialog = DatePickerDialog(
                this@MainActivity,
                datePickerListener,
                2020, 4, 1
            )
            datePickerDialog.show()
        }
    }

    internal inner class TimePickerButtonHandler : View.OnClickListener {
        override fun onClick(view: View) {
            val c = Calendar.getInstance()
            val hour = c[Calendar.HOUR_OF_DAY]
            val minute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this@MainActivity,
                OnTimeSetListener { view, selectedHour, selectedMinute ->
                    val toast = Toast.makeText(
                        this@MainActivity,
                        "selected $selectedHour:$selectedMinute",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                }, hour, minute, false
            )
            timePickerDialog.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain reference to text view label
        text = findViewById(R.id.textView)

        // Set up buttons with handlers
        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener(AddButtonHandler())
        val subButton = findViewById<Button>(R.id.sub)
        subButton.setOnClickListener(SubtractButtonHandler())
        val showButton = findViewById<Button>(R.id.show)
        showButton.setOnClickListener(ToastButtonHandler())
        val showDatePickerButton =
            findViewById<Button>(R.id.datePickerButton)
        showDatePickerButton.setOnClickListener(DatePickerButtonHandler())
        val showTimePicker =
            findViewById<Button>(R.id.timePicker)
        showTimePicker.setOnClickListener(TimePickerButtonHandler())
        setupDialogButton()
    }

    private fun setupDialogButton() {
        val message = findViewById<TextView>(R.id.message)
        val button = findViewById<Button>(R.id.dialogButton)
        button.setOnClickListener {
            val builder =
                AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Exit")
            builder.setMessage("Do you wish to Exit?")
            // set three option buttons
            builder.setPositiveButton(
                "Yes"
            ) { dialog, whichButton ->
                val msg = "YES $whichButton"
                message.setText(msg)
            }
            builder.setNeutralButton(
                "Cancel"
            ) { dialog, whichButton ->
                val msg = "CANCEL $whichButton"
                message.setText(msg)
            }
            builder.setNegativeButton(
                "NO"
            ) { dialog, whichButton ->
                val msg = "NO $whichButton"
                message.setText(msg)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}