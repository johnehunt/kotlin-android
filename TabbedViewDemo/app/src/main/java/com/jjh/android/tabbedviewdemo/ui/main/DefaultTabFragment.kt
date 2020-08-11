package com.jjh.android.tabbedviewdemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjh.android.tabbedviewdemo.R

/**
 * A simple [Fragment] subclass.
 * Use the [DefaultTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DefaultTabFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.default_tab_fragment, container, false)
    }

}