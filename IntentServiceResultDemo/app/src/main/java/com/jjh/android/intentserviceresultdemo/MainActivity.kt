package com.jjh.android.intentserviceresultdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The purpose of this demonstration application
 * is to illustrate how an IntentService can return
 * a result back to a triggering activity.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var receiver: ResultReceiver? = null

    // Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")

        // Set up receiver
        receiver = FactorialResultReceiver(Handler())
    }

    // Button handler methods
    fun onClickStartService(v: View?) {
        Log.d(TAG, "onClickStartService()")
        val numberToCalculate = inputAsInt
        // Create intent to launch service
        val intent = Intent(this, FactorialCalculationService::class.java)
        // Register the receiver
        intent.putExtra(FactorialCalculationService.RECEIVER, receiver)
        // Add data to be used by service
        intent.putExtra(FactorialCalculationService.NUMBER, numberToCalculate)
        // Start the service
        startService(intent)
    }

    private val inputAsInt: Int
        private get() {
            Log.d(TAG, "getInputAsInt()")
            val inputString = input.text.toString()
            return inputString.toInt()
        }

    /**
     * Inner class to handle result
     *
     * Create a new ResultReceive to receive results.  Your
     * .onReceiveResult method will be called from the thread running
     * handler if given, or from an arbitrary thread if null.
     */
    private inner class FactorialResultReceiver(handler: Handler?) :
        ResultReceiver(handler) {

        init {
            Log.d(TAG, "FactorialResultReceiver.<init>()")
        }

        // Will be called when the Service returns a result
        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            Log.d(TAG, "FactorialResultReceiver.onReceiveResult()")
            when (resultCode) {
                FactorialCalculationService.SUCCESS -> {
                    val calculatedTotal = resultData.getInt(FactorialCalculationService.RESULT)
                    // update the display
                    result.text = calculatedTotal.toString()
                }
                FactorialCalculationService.ERROR -> Toast.makeText(
                    applicationContext, "Error in Calculation",
                    Toast.LENGTH_SHORT
                ).show()
            }
            super.onReceiveResult(resultCode, resultData)
        }

    }

}