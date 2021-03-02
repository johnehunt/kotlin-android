package com.jjh.android.daggerdemo2

import android.os.Bundle
import android.util.Log
import com.jjh.android.daggerdemo2.dagger.services.ShoppingService
import com.jjh.android.daggerdemo2.dagger.services.UserService
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
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

