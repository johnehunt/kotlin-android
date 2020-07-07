package com.jjh.android.boundservicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.boundservicedemo.BoundService.DemoBinder

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var service: BoundService? = null
    private var message: TextView? = null

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection: ServiceConnection = ServiceConnectionHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        message = findViewById(R.id.message)
    }

    fun unbindServiceButtonClick(v: View?) {
        Log.d("Unbind Service button", "onClick()")
        if (service != null) {
            // Disconnect from service - if only one 'may' stop
            unbindService(connection)
            service = null
        }
    }

    fun onPrintDataButtonClick(v: View?) {
        Log.d(TAG, "onClick()")
        if (service != null) {
            message!!.text = service!!.date.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
        // Create intent to bind to service
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
        if (service != null) {
            // Unbind from service is still using it
            unbindService(connection)
        }
    }

    // Inner classes
    private inner class ServiceConnectionHandler : ServiceConnection {
        override fun onServiceConnected(
            className: ComponentName,
            binder: IBinder
        ) {
            Log.d(TAG, "onServiceConnected()")
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val demoBinder = binder as DemoBinder
            service = demoBinder.service
            Log.d("ServiceConnection", "service bound")
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

}