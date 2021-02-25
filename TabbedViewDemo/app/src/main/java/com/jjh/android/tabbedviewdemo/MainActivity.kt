package com.jjh.android.tabbedviewdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jjh.android.tabbedviewdemo.ui.main.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)

        // Set up the ViewPager with adapter
        viewPager.adapter = TabPagerAdapter(this)
        // Set the mediator to determine how the tab labels are formatted
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Tab 1"
                    1 -> tab.text = "Tab 2"
                    2 -> tab.text = "Tab 3"
                }
            }).attach()
    }

}