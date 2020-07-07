package com.jjh.android.helloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyLifecycleObserver())

        Log.d("MainActivity Lifecycle", "onCreate()")

        savedInstanceState?.let {
            val msg = it.getString("msg")
            Log.d("MainActivity Lifecycle", "onCreate() - $msg")
        }
    }

    // Restoring / Saving Dynamic State
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("MainActivity Lifecycle", "onRestoreInstanceState()")
        val msg = savedInstanceState.getString("msg")
        Log.d("MainActivity Lifecycle", "onRestoreInstanceState() - $msg")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("msg", "Hello Message " + Date())
        Log.d("MainActivity Lifecycle", "onSaveInstanceState()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity Lifecycle", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity Lifecycle", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity Lifecycle", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity Lifecycle", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity Lifecycle", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity Lifecycle", "onDestroy()")
    }
}