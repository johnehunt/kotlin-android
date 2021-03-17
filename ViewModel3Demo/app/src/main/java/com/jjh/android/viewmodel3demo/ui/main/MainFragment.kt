package com.jjh.android.viewmodel3demo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.jjh.android.viewmodel3demo.R
import com.jjh.android.viewmodel3demo.databinding.MainFragmentBinding
import com.jjh.android.viewmodel3demo.BR.myViewModel

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        // Bind the view model to the layout variable myViewModel
        binding.setVariable(myViewModel, viewModel)
    }

}