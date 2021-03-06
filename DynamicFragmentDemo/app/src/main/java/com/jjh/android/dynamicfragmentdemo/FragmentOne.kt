package com.jjh.android.dynamicfragmentdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjh.android.dynamicfragmentdemo.databinding.FragmentOneBinding

class FragmentOne : Fragment() {

    companion object {
        private const val TAG = "FragmentOne"
    }

    init {
        Log.d(TAG, "init{}")
    }

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    // On callback methods - defined in order called
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        _binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")
        _binding = null
    }

}