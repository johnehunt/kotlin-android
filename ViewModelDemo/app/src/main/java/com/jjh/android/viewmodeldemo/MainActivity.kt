package com.jjh.android.viewmodeldemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.viewmodeldemo.ui.main.MainFragment

/**
 * Illustrates the use of a ViewModel
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
        setContentView(R.layout.main_activity)
    }

}