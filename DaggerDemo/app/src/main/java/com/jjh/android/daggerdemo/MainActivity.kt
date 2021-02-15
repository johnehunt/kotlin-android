package com.jjh.android.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DaggerDemo"
    }

    @Inject
    lateinit var registrationModel: RegistrationModel

    @Inject
    lateinit var statusManager: StatusManager

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        Log.d(TAG, "onCreate - Triggering Dagger to inject into the Activity")
        // Ask Dagger to inject our dependencies
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate - accessing injected objects")
        Log.d(TAG, "onCreate - ${registrationModel}")
        Log.d(TAG, "onCreate - ${statusManager}")

        registrationModel.logMe()

        Log.d(TAG, "onCreate - accessing injected object")
        statusManager.logStatus()

    }

}