package com.jjh.android.boundservicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.boundservicedemo.BoundService.DemoBinder
import com.jjh.android.boundservicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private var service: BoundService? = null

    /** Defines callbacks for service binding, passed to bindService()  */
    private lateinit var connection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onStartServiceButtonClick(v: View) {
        Log.d(TAG, "onStartServiceButtonClick()")
        // Create intent to bind to service
        val intent = Intent(this, BoundService::class.java)
        connection = ServiceConnectionHandler().apply {
            bindService(intent, this, Context.BIND_AUTO_CREATE)
        }
    }

    fun onPrintDataButtonClick(v: View) {
        Log.d(TAG, "onPrintDataButtonClick()")
        service?.let{
            binding.message.text = it.date.toString()
        }
    }

    fun unbindServiceButtonClick(v: View) {
        Log.d(TAG, "unbindServiceButtonClick()")
        service?.let{
            unbindService(connection!!)
        }
        service = null
    }

    // Inner classes
    private inner class ServiceConnectionHandler : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, binder: IBinder) {
            Log.d(TAG, "ServiceConnectionHandler.onServiceConnected()")
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val demoBinder = binder as DemoBinder
            service = demoBinder.service
            Log.d(TAG, "ServiceConnectionHandler - service bound")
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

}