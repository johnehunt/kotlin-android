package com.jjh.android.kittykat

import android.os.Bundle
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Boiler plate code for setting up gestures
        // Setup Gesture Handler
        val handler = GestureHandler()
        val detector = GestureDetectorCompat(this, handler)
        detector.setOnDoubleTapListener(handler)

        // Set up Image with Gesture Handler
        image.setOnTouchListener { view, event -> detector.onTouchEvent(event) }

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

    private fun showMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT).show()
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
    inner class GestureHandler : SimpleOnGestureListener() {
        /**
         * Notified of a fling event when it occurs with the initial on down
         * MotionEvent and the matching up MotionEvent. true indicates that
         * the app has consumed the event - don't need to pass it on.
         */
        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            showMessage("Purrr")
            return true
        }

        /**
         * Invoked when a double-tap gesture occur.
         */
        override fun onDoubleTap(e: MotionEvent): Boolean {
            showMessage("Ouch!")
            return true
        }
    }

    /**
     * Example of Pinch gesture detection
     */
    inner class MyPinchListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            showMessage("PINCH! OUCH!")
            return true
        }
    }
}