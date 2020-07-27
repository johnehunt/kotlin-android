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

    }

}