package com.jjh.android.mediaplayerdemo

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    // Lifecycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Determine which buttons are enabled at start
        pause.isEnabled = false
        stop.isEnabled = false

        // Setup Media Player - note must manually add raw folder
        // under the res folder in your project
        // place song.mp3 file in here
        mediaPlayer = MediaPlayer.create(this, R.raw.song)

        // Set up callback listeners
        mediaPlayer.setOnErrorListener(CustomMediaPlayerOnErrorListener())
        mediaPlayer.setOnInfoListener(CustomMediaPlayerInfoListener())
        mediaPlayer.setOnPreparedListener(CustomerMediaPlayerOnPreparedListener())
        mediaPlayer.setOnCompletionListener(CustomerMediaPlayerOnCompletionListener())
    }

    override fun onDestroy() {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        } catch (exp: Exception) {
            Toast.makeText(
                applicationContext,
                "Problem starting source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }
        super.onDestroy()
    }

    // Button Handler methods
    fun onPlayButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Playing sound", Toast.LENGTH_SHORT).show()
        try {
            // Can throw IllegalStateException if it is called in an invalid state
            mediaPlayer.start()
        } catch (exp: IllegalStateException) {
            Toast.makeText(
                applicationContext,
                "Problem starting source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }

        // Enable / Disable appropriate buttons
        pause.isEnabled = true
        play.isEnabled = false
        stop.isEnabled = true
    }

    fun onPauseButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Pausing sound", Toast.LENGTH_SHORT).show()
        mediaPlayer.pause()

        // Enable / Disable buttons
        pause.isEnabled = false
        play.isEnabled = true
        stop.isEnabled = false
    }

    fun onStopButtonClick(v: View?) {
        Toast.makeText(applicationContext, "Stopping sound", Toast.LENGTH_SHORT).show()
        try {
            mediaPlayer.stop()
            // Need to prepare mediaPlayer
            // so that it can play another tune
            mediaPlayer.prepare()
        } catch (exp: Exception) {
            Toast.makeText(
                applicationContext,
                "Problem playing source " + exp.message,
                Toast.LENGTH_LONG
            ).show()
        }

        // Enable / Disable buttons
        pause.isEnabled = false
        play.isEnabled = true
        stop.isEnabled = false
    }
    // Media Player callback listeners
    /**
     * Interface definition of a callback to be invoked when there has been an
     * error during an asynchronous operation
     */
    internal inner class CustomMediaPlayerOnErrorListener :
        MediaPlayer.OnErrorListener {
        /**
         * what - the type of error that has occurred:
         * extra, error code specific to the error. Typically implementation dependent.
         * return - True if the method handled the error, false if it didn't.
         */
        override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
            Log.e(
                "CustomMediaPlayerOnErrorListener",
                "what:$what, extra:$extra"
            )
            return true
        }
    }

    /**
     * Used by mediaplayer to communicates some
     * info and/or warning about the media or its playback.
     */
    internal inner class CustomMediaPlayerInfoListener : MediaPlayer.OnInfoListener {
        override fun onInfo(mp: MediaPlayer, what: Int, extra: Int): Boolean {
            Log.e("CustomMediaPlayerInfoListener", "what:$what, extra:$extra")
            return true
        }
    }

    /**
     * Run when the mediaplayer is 'prepared' - can be used with prepareAsync() to get notification
     * of when Mediaplayer is ready to start playing, e.g.
     *
     * mediaPlayer.prepareAsync();
     *
     * then in onPrepared call mp.start();
     */
    internal inner class CustomerMediaPlayerOnPreparedListener : OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer) {
            Log.e("CustomerMediaPlayerOnPreparedListener", mp.toString())
        }
    }

    /**
     * Called when Media player completes playing.
     */
    internal inner class CustomerMediaPlayerOnCompletionListener :
        OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer) {
            Log.e("CustomerMediaPlayerOnCompletionListener", mp.toString())
            play.isEnabled = true
            pause.isEnabled = false
            stop.isEnabled = false
        }
    }
}