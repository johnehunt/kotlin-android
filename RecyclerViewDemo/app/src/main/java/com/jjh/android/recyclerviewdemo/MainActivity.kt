package com.jjh.android.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        setContentView(R.layout.main_activity)

        // Access RecyclerView element and set up layout and adapter
        val playRecyclerView = findViewById<RecyclerView>(R.id.play_recycler_view)
        playRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PlayAdapter()
        }

    }

}