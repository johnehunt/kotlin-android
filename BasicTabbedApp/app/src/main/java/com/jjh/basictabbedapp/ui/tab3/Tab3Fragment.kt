package com.jjh.basictabbedapp.ui.tab3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjh.basictabbedapp.R

class Tab3Fragment : Fragment() {

    companion object {
        fun newInstance() = Tab3Fragment()
    }

    private lateinit var viewModel: Tab3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab3_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Tab3ViewModel::class.java)
    }

}
