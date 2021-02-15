package com.jjh.android.intentserviceresultdemo

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log

class FactorialCalculationService :
    IntentService(FactorialCalculationService::class.java.simpleName) {

    companion object {
        private const val TAG = "FactorialCalculationSer"

        // Set up some constants for Activity to use
        const val SUCCESS = 1
        const val ERROR = 2
        const val NUMBER = "number"
        const val RESULT = "result"
        const val RECEIVER = "receiver"
    }

    init {
        Log.d(TAG, "<init>()")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent()")
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        intent?.let {
            val numberToCalculate = it.getIntExtra(NUMBER, 0)
            val result = factorial(numberToCalculate)

            // Now need to return result from the service
            val receiver = it.getParcelableExtra<ResultReceiver>(RECEIVER)
            // Set up data to return
            val bundle = Bundle()
            bundle.putInt(RESULT, result)

            // Return results and indication of status
            receiver.send(SUCCESS, bundle)
        }
    }

    private fun factorial(num: Int): Int {
        Log.d(TAG, "factorial($num)")
        var result = 1
        if (num > 0) {
            result = 1
            for (i in 1..num) {
                result *= i
            }
        }
        return result
    }

}