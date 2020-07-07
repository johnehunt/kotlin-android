package com.jjh.android.boundservicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.jjh.android.boundservicedemo.BoundService
import java.util.*

class BoundService : Service() {

    companion object {
        private const val TAG = "BoundService"
    }

    // Binding support
    private val binder: IBinder = DemoBinder()

    inner class DemoBinder : Binder() {
        val service: BoundService
            get() = this@BoundService
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind()")
        return binder
    }

    override fun onRebind(intent: Intent) {
        Log.d(TAG, "onRebind()")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind()")
        return true
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    // Functionality offered by service
    val date: Date
        get() {
            Log.d(TAG, "getDate()")
            return Date()
        }

}