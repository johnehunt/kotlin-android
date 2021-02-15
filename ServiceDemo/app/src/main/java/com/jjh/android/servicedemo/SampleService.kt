package com.jjh.android.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Illustrates Standard Service
 */
class SampleService : Service() {

    companion object {
        private const val TAG = "SampleService"
    }

    // Have to implement as abstract method in Service
    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind()")
        return null // Must return null for a standard started service
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

}