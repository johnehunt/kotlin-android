package com.jjh.android.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val CHANNEL_ID = "com.jjh.android.notificationdemo"
    }

    private var notificationManager: NotificationManager? = null
    private val notificationID = 101

    fun onButtonClick(v: View?) {
        Log.d(TAG, "onButtonClick() - setting up notification")
        val notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("A Title")
                .setContentText("This is an example : Hello There!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(CHANNEL_ID)
                .setNumber(10)
                .build()
        Log.d(TAG, "onButtonClick() - triggering notification")
        notificationManager!!.notify(notificationID, notification)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel()")
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "createNotificationChannel() - setting up channel")

            // Set up channel
            val name = "Notify Demo Info"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            // Configure channel
            channel.description = "EXAMPLE NOTIFICATION CHANNEL"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 40, 300, 200, 400)

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager!!.createNotificationChannel(channel)
        }
    }

}