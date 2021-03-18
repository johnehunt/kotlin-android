package com.jjh.basictabbedapp.ui.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jjh.basictabbedapp.ui.tab1.Tab1Fragment
import com.jjh.basictabbedapp.ui.tab2.Tab2Fragment
import com.jjh.basictabbedapp.ui.tab3.Tab3Fragment

class TabPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

  companion object {
    private const val TAG = "TabPagerAdapter"
  }

  override fun createFragment(position: Int): Fragment {
    Log.d(TAG, "createFragment($position)")
    return when (position) {
      0 -> Tab1Fragment()
      1 -> Tab2Fragment()
      else -> Tab3Fragment()
    }
  }

  override fun getItemCount(): Int {
    Log.d(TAG, "getItemCount()")
    return 3
  }
}
