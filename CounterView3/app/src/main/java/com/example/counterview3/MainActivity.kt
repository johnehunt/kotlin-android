package com.example.counterview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.counterview3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var countValue = 0

    // Declare view binding property
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        // Set up view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    // Button click handlers

    fun add(view: View) {
        Log.d(TAG, "add")
        countValue++
        binding.countView.text = countValue.toString()
    }

    fun sub(view: View) {
        Log.d(TAG, "sub")
        countValue--
        binding.countView.text = countValue.toString()
    }
}