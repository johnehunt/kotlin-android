package com.jjh.android.servicedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var startServiceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Button handler methods
    fun onStartButtonClick(v: View) {
        try {
            Log.d(TAG, "Starting service!")
            startServiceIntent = Intent(this, SampleService::class.java)
            startService(startServiceIntent)
            message.text = "Started Service"
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun onStopButtonClick(v: View) {
        try {
            Log.d(TAG, "Stopping service!")
            stopService(startServiceIntent)
            message.text = "Stopped Service"
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}