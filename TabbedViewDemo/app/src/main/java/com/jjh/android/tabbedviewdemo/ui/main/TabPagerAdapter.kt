package com.jjh.android.tabbedviewdemo.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jjh.android.tabbedviewdemo.R

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class TabPagerAdapter(private val context: Context,
                      fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val TAG = "TabPagerAdapter"
        private val TAB_TITLES =
            arrayOf(R.string.tab_title_text_1,
                    R.string.tab_title_text_2,
                    R.string.tab_title_text_3)
        private val TAB_FRAGMENTS =
            arrayOf(Tab1Fragment(),
                    Tab2Fragment(),
                    Tab3Fragment())
    }

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "getItem($position)")
        return TAB_FRAGMENTS[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.d(TAG, "getPageTitle($position)")
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        Log.d(TAG, "getCount()")
        return TAB_FRAGMENTS.size
    }
}