package com.jjh.android.tabbedviewdemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jjh.android.tabbedviewdemo.R

/**
 * A simple [Fragment] subclass.
 * Use the [Tab2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab2_fragment, container, false)
    }

}