package com.jjh.android.dynamicfragmentdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MyActivity"
        // Store fragment instances so that they can be reused
        // make use of lifecycle member functions to handle
        // setting / resetting any state, behaviour etc.
        private val FRAGMENT_ONE = FragmentOne()
        private val FRAGMENT_TWO = FragmentTwo()
    }

    fun onFragmentOneButtonClick(v: View) {
        Log.d(TAG, "onClick()")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, FRAGMENT_ONE)
        transaction.commit()
    }

    fun onFragmentTwoButtonClick(v: View) {
        Log.d(TAG, "onClick()")
        // Uses flow style behaviour for concise code
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentLayout, FRAGMENT_TWO)
            .commitNow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
    }

}