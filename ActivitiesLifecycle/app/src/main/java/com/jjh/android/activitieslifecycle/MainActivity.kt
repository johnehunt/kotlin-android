package com.jjh.android.activitieslifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.helloworld.MyLifecycleObserver
import java.util.Date

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lifecycle.addObserver(MyLifecycleObserver())

        Log.d(TAG, "onCreate()")

        savedInstanceState?.apply {
            val msg = getString("msg")
            Log.d(TAG, "onCreate() - $msg")
        }
    }

    // Restoring / Saving Dynamic State
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState()")
        val msg = savedInstanceState.getString("msg")
        Log.d(TAG, "onRestoreInstanceState() - $msg")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("msg", "Hello Message " + Date())
        Log.d(TAG, "onSaveInstanceState()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}