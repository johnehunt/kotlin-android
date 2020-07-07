package com.jjh.android.viewmodeldemo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    val viewModel: CounterViewModel by viewModels()

    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.textView)

    }

    /**
     * Member function to handle button click
     * Uses viewModel to update data and refresh screen
     */
    fun addButtonHandler(view: View) {
        viewModel.increment()
        text.text = viewModel.count.toString()
    }
}