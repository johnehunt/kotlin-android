package com.jjh.android.counterview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var countValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        // Using Android extensions to access countView
        countView.text = countValue.toString()
    }

    // Button click handlers

    fun add(view: View) {
        Log.d(TAG, "add")
        countValue++
        countView.text = countValue.toString()
    }

    fun sub(view: View) {
        Log.d(TAG, "sub")
        countValue--
        countView.text = countValue.toString()
    }

}