package com.jjh.android.viewmodel2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

/**
 * Illustrates use of a ViewModel and LiveData
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
        setContentView(R.layout.main_activity)
    }
}