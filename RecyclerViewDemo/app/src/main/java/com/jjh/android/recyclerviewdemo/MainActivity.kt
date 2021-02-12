package com.jjh.android.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.main_activity)

        // Access RecyclerView element and set up layout and adapter
        play_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PlayAdapter()
        }

    }
}