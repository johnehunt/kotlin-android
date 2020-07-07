package com.jjh.android.mediaplayerthreadeddemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.mediaplayerthreadeddemo.PlayerService.DemoBinder

class MainActivity : AppCompatActivity() {

    var service: PlayerService? = null
    private var pause: Button? = null
    private var play: Button? = null
    private var stop: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup button references
        pause = findViewById(R.id.pause)
        play = findViewById(R.id.play)
        stop = findViewById(R.id.stop)

        // Determine which buttons are enabled at start
        pause?.setEnabled(false)
        stop?.setEnabled(false)

        // Create intent to bind to service
        val intent = Intent(this, PlayerService::class.java)
        bindService(intent, ServiceConnectionHandler(), Context.BIND_AUTO_CREATE)
    }

    // Button Handler methods
    fun onPlayButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Playing sound", Toast.LENGTH_SHORT).show()
        try {
            service!!.start()
        } catch (exp: Exception) {
            Toast.makeText(
                applicationContext,
                "Problem starting source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }

        // Enable / Disable appropriate buttons
        pause!!.isEnabled = true
        play!!.isEnabled = false
        stop!!.isEnabled = true
    }

    fun onPauseButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Pausing sound", Toast.LENGTH_SHORT).show()
        service!!.pause()

        // Enable / Disable buttons
        pause!!.isEnabled = false
        play!!.isEnabled = true
        stop!!.isEnabled = false
    }

    fun onStopButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Stopping sound", Toast.LENGTH_SHORT).show()
        service!!.stop()
        try {
            // Need to prepare mediaPlayer
            // so that it can play another tune
            service!!.prepare()
        } catch (exp: Exception) {
            Toast.makeText(
                applicationContext,
                "Problem playing source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }

        // Enable / Disable buttons
        pause!!.isEnabled = false
        play!!.isEnabled = true
        stop!!.isEnabled = false
    }

    // Service Connection Listener
    private inner class ServiceConnectionHandler : ServiceConnection {
        override fun onServiceConnected(
            className: ComponentName,
            binder: IBinder
        ) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val demoBinder = binder as DemoBinder
            service = demoBinder.service
            Log.d("ServiceConnection", "service bound")
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }
}