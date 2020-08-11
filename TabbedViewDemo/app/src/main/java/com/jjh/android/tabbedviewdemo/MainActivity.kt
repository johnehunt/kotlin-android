package com.jjh.android.tabbedviewdemo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.tabbedviewdemo.ui.main.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the ViewPager with an adapter
        viewPager.adapter = TabPagerAdapter(this, supportFragmentManager)
        // Set the viewPager for the tablayout
        tab_layout.setupWithViewPager(viewPager)
    }
}