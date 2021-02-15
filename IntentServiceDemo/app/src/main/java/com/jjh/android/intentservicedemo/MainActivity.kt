package com.jjh.android.intentservicedemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Button handler methods
    fun onClickStartService(v: View) {
        // Create intent to launch service
        val intent = Intent(this, LoggerIntentService::class.java)
        startService(intent)
    }
}