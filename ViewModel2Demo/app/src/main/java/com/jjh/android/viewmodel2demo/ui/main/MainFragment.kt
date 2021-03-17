package com.jjh.android.viewmodel2demo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.jjh.android.viewmodel2demo.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"
    }
    private val viewModel by viewModels<MainViewModel>()

    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView")
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

//        val resultObserver = Observer<Double> { value -> binding.dollarTextView.text = "$%.2f".format(value) }
//        // viewLifecycleOwner inherited from Fragment
//        viewModel.dollarValue.observe(viewLifecycleOwner, resultObserver)

        // Shorthand form - sets up one way data binding
        viewModel.dollarValue
                 .observe(viewLifecycleOwner) {
                     binding.dollarTextView.text = "$%.2f".format(it)
                 }

        binding.convertButton.setOnClickListener {
            Log.d(TAG, "convertButton click handler")
            if (binding.sterlingEditText.text.isNotEmpty()) {
                viewModel.sterlingValue = binding.sterlingEditText.text.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}