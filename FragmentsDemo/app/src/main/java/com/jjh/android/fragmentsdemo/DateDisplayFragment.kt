package com.jjh.android.fragmentsdemo

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.Date

import kotlinx.android.synthetic.main.fragment_date_display.*

class DateDisplayFragment : Fragment() {
    companion object {
        private const val TAG = "DateDisplayFragment"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_date_display, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach()")
    }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        Log.d(TAG, "onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()")
        val time = SimpleDateFormat("dd MM yyyy HH:mm:ss").format(Date())
        textView.text = time
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")
    }

}