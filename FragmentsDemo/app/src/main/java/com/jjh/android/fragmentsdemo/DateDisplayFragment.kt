package com.jjh.android.fragmentsdemo

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjh.android.fragmentsdemo.databinding.FragmentDateDisplayBinding

import java.util.Date

class DateDisplayFragment : Fragment() {

    companion object {
        private const val TAG = "DateDisplayFragment"
    }

    private var _binding: FragmentDateDisplayBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach()")
    }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        Log.d(TAG, "onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              state: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        _binding = FragmentDateDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")
        val time = SimpleDateFormat("dd MM yyyy HH:mm:ss").format(Date())
        binding.textView.text = time
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