package com.jjh.android.mediaplayerthreadeddemo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class PlayerService : Service() {

    private val binder: IBinder = DemoBinder()

    var mediaPlayer: MediaPlayer? = null

    // Flag to indicate whether MediaPlayer has completed initial preparation
    // or not.
    private val playerAvailable =
        AtomicBoolean(false)

    // Handles execution of all threads
    private val executor: ExecutorService

    // Service Functionality - each runs in a background thread
    fun start() {
        if (playerAvailable.get()) {
            val r = Runnable { mediaPlayer!!.start() }
            executor.execute(r)
        }
    }

    fun pause() {
        if (playerAvailable.get()) {
            val r = Runnable { mediaPlayer!!.pause() }
            executor.execute(r)
        }
    }

    fun stop() {
        if (playerAvailable.get()) {
            val r = Runnable { mediaPlayer!!.stop() }
            executor.execute(r)
        }
    }

    fun prepare() {
        if (playerAvailable.get()) {
            val r = Runnable {
                try {
                    // Need to prepare mediaPlayer
                    // so that it can play another tune
                    mediaPlayer!!.prepare()
                    Log.d("mytrace", "prepare()")
                } catch (exp: Exception) {
                    Log.d("PlayerService", "MediaPlayer problem", exp)
                }
            }
            executor.execute(r)
        }
    }

    inner class DemoBinder : Binder() {
        val service: PlayerService
            get() = this@PlayerService
    }

    // Lifecycle Methods
    override fun onCreate() {
        super.onCreate()
        val t = PlayerHandlerThread()
        t.start()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind()")
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind()")
        return true
    }

    override fun onDestroy() {
        try {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
            playerAvailable.set(false)
            mediaPlayer!!.release()
            mediaPlayer = null
        } catch (exp: Exception) {
            Toast.makeText(
                applicationContext,
                "Problem starting source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }
        super.onDestroy()
    }

    // Handler Thread class
    // Need to run in a HandlerThread for call backs to run in this thread
    // Handler threads gave a looper which is required to ensure callback
    // methods don't run on the UI thread.
    // class PlayerHandlerThread extends Thread {
    internal inner class PlayerHandlerThread : HandlerThread("PlayerHandlerThread") {
        // public void run() {
        public override fun onLooperPrepared() {
            mediaPlayer = MediaPlayer.create(this@PlayerService, R.raw.song)
            mediaPlayer!!.setOnPreparedListener { mp ->
                Log.e("mytrace", mp.toString())
                playerAvailable.set(true)
            }
        }
    }

    companion object {
        private const val TAG = "PlayerService"
    }

    init {
        // ensures a single thread is reused and runnables
        // executed in sequence submitted
        executor = Executors.newSingleThreadExecutor()
    }
}