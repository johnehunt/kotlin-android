package com.jjh.android.viewmodel2demo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jjh.android.viewmodel2demo.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

//        val resultObserver = Observer<Double> { value -> dollarTextView.text = "$%.2f".format(value) }
//        // viewLifecycleOwner inherited from Fragment
//        viewModel.dollarValue.observe(viewLifecycleOwner, resultObserver)

        // Shorthand form - sets up one way data binding
        viewModel.dollarValue
                 .observe(viewLifecycleOwner) {
                     dollarTextView.text = "$%.2f".format(it)
                 }

        convertButton.setOnClickListener {
            Log.d(TAG, "convertButton click handler")
            if (sterlingEditText.text.isNotEmpty()) {
                viewModel.sterlingValue = sterlingEditText.text.toString()
            }
        }
    }

}