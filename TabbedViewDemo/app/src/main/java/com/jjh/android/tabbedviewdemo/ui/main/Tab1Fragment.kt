package com.jjh.android.tabbedviewdemo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjh.android.tabbedviewdemo.R

/**
 * A placeholder fragment containing a simple view.
 */
class Tab1Fragment : Fragment() {

    companion object {
        private const val TAG = "Tab1Fragment"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.tab1_fragment, container, false)
    }

}