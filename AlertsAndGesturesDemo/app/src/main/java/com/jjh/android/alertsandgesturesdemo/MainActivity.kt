package com.jjh.android.alertsandgesturesdemo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast(view: View) {
        val toast = Toast.makeText(
            this,
            "Name: ${inputName.text}",
            Toast.LENGTH_LONG)
        toast.setGravity(
            Gravity.CENTER,
            toast.xOffset / 2,
            toast.yOffset / 2)
        toast.show()
    }

    fun showCustomDialog(view: View) {
        // Obtain a builder instance
        val builder = AlertDialog.Builder(this)
        // configure builder settings
        builder.setTitle("Continue")
        builder.setMessage("Do you want to Continue?")
        // set three option buttons
        builder.setPositiveButton("Yes") { dialog, button ->
            val msg = "YES $button"
            message.text = msg
        }
        builder.setNeutralButton("Cancel") { dialog, button ->
            val msg = "CANCEL $button"
            message.text = msg
        }
        builder.setNegativeButton("No") { dialog, button ->
            val msg = "NO $button"
            message.text = msg
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun showDatePicker(view: View) {
        val datePickerListener =
            OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                // when dialog box is closed, below method will be called.
                Toast.makeText(
                    this,
                    "selected $selectedYear, $selectedMonth, $selectedDay",
                    Toast.LENGTH_LONG).show()
            }
        DatePickerDialog(
            this,
            datePickerListener,
            2021, 2, 14).show()
    }

    fun showTimePicker(view: View) {
        val c = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]

        // Launch Time Picker Dialog
        TimePickerDialog(
            this,
            OnTimeSetListener { view, selectedHour, selectedMinute ->
                Toast.makeText(
                    this,
                    "selected $selectedHour:$selectedMinute",
                    Toast.LENGTH_LONG
                ).show()
            },
            hour,
            minute, true).show()
    }

}