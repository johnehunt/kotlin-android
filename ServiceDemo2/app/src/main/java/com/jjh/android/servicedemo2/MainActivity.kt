package com.jjh.android.servicedemo2

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sampleServiceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up broadcast receiver - programmatically
        // Required as of Android 8.0 (and above) since this version
        // most implicit broadcasts need to be registered dynamically
        // and not statically (in the manifest).
        val filter = IntentFilter("com.jjh.android.servicedemo.Message")
        val receiver = SampleBroadcastReceiver()
        registerReceiver(receiver, filter, null, null)
        Log.d("SD - MainActivity", "onCreate()")
    }

    fun onStartButtonClick(v: View?) {
        try {
            Log.d("SD - MainActivity", "Starting service!")
            sampleServiceIntent = Intent(this, SampleService2::class.java)
            startService(sampleServiceIntent)
            textView.text = "Started Service"
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun onStopButtonClick(v: View?) {
        try {
            Log.d("SD - MainActivity", "Stopping service!")
            stopService(sampleServiceIntent)
            textView.text = "Stopped Service"
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}