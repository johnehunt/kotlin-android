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

        viewPager.adapter = TabPagerAdapter(this, supportFragmentManager)

        tab_layout.setupWithViewPager(viewPager)

    }
}