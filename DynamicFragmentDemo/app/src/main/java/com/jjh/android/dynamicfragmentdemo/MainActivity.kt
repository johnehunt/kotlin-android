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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, FragmentOne())
        transaction.commit()
    }

    fun onFragmentTwoButtonClick(v: View?) {
        Log.d(TAG, "onClick()")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, FragmentTwo())
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
    }

}