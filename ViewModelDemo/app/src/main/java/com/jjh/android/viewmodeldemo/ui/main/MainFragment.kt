package com.jjh.android.viewmodeldemo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jjh.android.viewmodeldemo.R

import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    // private lateinit var viewModel: MainViewModel
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        // Older style of access to view model
        // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        dollarTextView.text = "$%.2f".format(viewModel.result)

        convertButton.setOnClickListener {
            Log.d(TAG, "convertButton click handler")
            if (sterlingEditText.text.isNotEmpty()) {
                viewModel.amount = sterlingEditText.text.toString()
                dollarTextView.text = "$%.2f".format(viewModel.result)
            } else {
                dollarTextView.text = "No Value"
            }
        }
    }

}