package com.jjh.android.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jjh.android.daggerdemo.dagger.services.ShoppingService
import com.jjh.android.daggerdemo.dagger.services.UserService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DaggerDemo"
    }

    @Inject
    lateinit var shoppingService: ShoppingService

    @Inject
    lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        Log.d(TAG, "onCreate - Triggering Dagger to inject into the Activity")
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate - accessing injected objects")
        Log.d(TAG, "onCreate - ${userService}")
        Log.d(TAG, "onCreate - ${shoppingService}")

    }

}