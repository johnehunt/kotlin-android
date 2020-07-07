package com.jjh.android.kittykat

import android.os.Bundle
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Gesture Handler
        val handler = GestureHandler()
        val detector = GestureDetectorCompat(this, handler)
        detector.setOnDoubleTapListener(handler)

        // Set up Image with Gesture Handler
        val image = findViewById<ImageButton>(R.id.image)
        image.setOnTouchListener { v, me -> detector.onTouchEvent(me) }

        /**
         * Uncomment to try out pinch gesture
         */
//        final ScaleGestureDetector mScaleDetector =
//                new ScaleGestureDetector(this, new MyPinchListener());
//        image.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mScaleDetector.onTouchEvent(event);
//                return true;
//            }
//        });
    }

    /**
     * SimpleOneGestureListener is a convenience class used to provide default
     * implementations for all methods in the OnGestureListener interface
     * and the onDoubleTapListener interface:
     *
     * OnGestureListener used to notify when gestures occur.
     * OnDoubleTapListener is used to notify when a double-tap occurs.
     *
     */
    internal inner class GestureHandler : SimpleOnGestureListener() {
        /**
         * Notified of a fling event when it occurs with the initial on down
         * MotionEvent and the matching up MotionEvent. true indicates that
         * the app has consumed the event - don't need to pass it on.
         */
        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            Toast.makeText(
                this@MainActivity,
                "Purrr",
                Toast.LENGTH_SHORT).show()
            return true
        }

        /**
         * Invoked when a double-tap gesture occur.
         */
        override fun onDoubleTap(e: MotionEvent): Boolean {
            Toast.makeText(
                this@MainActivity,
                "Ouch!",
                Toast.LENGTH_SHORT).show()
            return true
        }
    }

    /**
     * Example of Pinch gesture detection
     */
    internal inner class MyPinchListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            Toast.makeText(
                this@MainActivity,
                "PINCH! OUCH!",
                Toast.LENGTH_SHORT).show()
            return true
        }
    }
}