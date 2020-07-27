package com.jjh.android.viewmodel2demo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjh.android.viewmodel2demo.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // result.text = viewModel.getResult().toString()

        val resultObserver = Observer<Double> {
            value -> result.text = value.toString()
        }
        viewModel.getResult().observe(viewLifecycleOwner, resultObserver) // viewLifecycleOwner inherited from Fragment

        convertButton.setOnClickListener {
            Log.d(TAG, "convertButton click handler")
            if (sterlingText.text.isNotEmpty()) {
                viewModel.setAmount(sterlingText.text.toString())
                // result.text = viewModel.getResult().toString()
            } else {
                result.text = "No Value"
            }
        }
    }



}