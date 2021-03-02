package com.jjh.android.daggerhiltdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.daggerhiltdemo.dagger.services.ShoppingService
import com.jjh.android.daggerhiltdemo.dagger.services.UserService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate - accessing injected objects")
        Log.d(TAG, "onCreate - ${userService}")
        Log.d(TAG, "onCreate - ${shoppingService}")

    }
}