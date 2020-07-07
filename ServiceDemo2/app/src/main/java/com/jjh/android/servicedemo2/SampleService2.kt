package com.jjh.android.servicedemo2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SampleService2 : Service() {
    private var isRunning = true
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("SD - SampleService2", "onStartCommand()")
        val thread = Thread(Broadcaster())
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SD - SampleService2", "onDestory()")
        isRunning = false
    }

    internal inner class Broadcaster : Runnable {
        override fun run() {
            Log.d("SD - Broadcaster", "Starting thread")
            var i = 0
            while ((i < 10) and isRunning) {
                try {
                    // Broadcast the message
                    Log.d("SD - Broadcaster", "Broadcasting Message")
                    val intent = Intent("com.jjh.servicedemo.Message")
                    val msg = "Hello $i"
                    intent.putExtra("serviceData", msg)
                    sendBroadcast(intent)
                    Thread.sleep(5000) // five seconds
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                i++
            }
        }
    }
}