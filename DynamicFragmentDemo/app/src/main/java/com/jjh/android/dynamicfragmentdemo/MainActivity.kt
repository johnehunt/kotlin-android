package com.jjh.android.dynamicfragmentdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MyActivity"
    }

    fun onFragmentOneButtonClick(v: View?) {
        Log.d(TAG, "onClick()")
        val fragment = FragmentOne()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.displaylayout, fragment)
        transaction.commit()
    }

    fun onFragmentTwoButtonClick(v: View?) {
        Log.d(TAG, "onClick()")
        val fragment = FragmentTwo()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.displaylayout, fragment)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
    }

}