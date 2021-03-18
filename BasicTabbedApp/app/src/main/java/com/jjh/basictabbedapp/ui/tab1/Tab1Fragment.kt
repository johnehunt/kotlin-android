package com.jjh.basictabbedapp.ui.tab1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjh.basictabbedapp.R

class Tab1Fragment : Fragment() {

    companion object {
        fun newInstance() = Tab1Fragment()
    }

    private lateinit var viewModel: TabItem1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab1_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TabItem1ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
