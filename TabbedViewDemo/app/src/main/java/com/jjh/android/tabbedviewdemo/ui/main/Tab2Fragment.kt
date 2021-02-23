package com.jjh.android.tabbedviewdemo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjh.android.tabbedviewdemo.R

class Tab2Fragment : Fragment() {

    companion object {
        private const val TAG = "Tab2Fragment"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.tab2_fragment, container, false)
    }

}