package com.jjh.android.intentservicedemo

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.util.*

class LoggerIntentService :
    IntentService(LoggerIntentService::class.java.simpleName) {

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val d = Date()
        Log.d("LoggerIntentService", "onHandleIntent() - $d")
    }

}