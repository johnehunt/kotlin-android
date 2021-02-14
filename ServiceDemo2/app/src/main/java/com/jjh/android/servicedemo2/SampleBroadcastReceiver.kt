package com.jjh.android.servicedemo2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SampleBroadcastReceiver : BroadcastReceiver() {

    // onReceive method runs on the main thread, and because of this,
    // its execution should be brief

    override fun onReceive(context: Context, intent: Intent) {
        val serviceData = intent.getStringExtra("serviceData")
        Log.d("SD - SampleBroadcastReceiver - onReceive()",
            "serviceData: >>> $serviceData")
        Toast.makeText(context, serviceData, Toast.LENGTH_LONG).show()
    }

}