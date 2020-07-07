package com.jjh.android.fragmentsdemo

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

class DateTimeFragment : Fragment() {
    private var time: String? = null

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        if (time == null) {
            time = SimpleDateFormat("dd MM yyyy HH:mm:ss").format(Date())
        }
        Log.d("DateTimeFragment", "onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = TextView(this.activity)
        view.text = time
        Log.d("DateTimeFragment", "onCreateView()")
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("DateTimeFragment", "onAttach()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DateTimeFragment", "onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("DateTimeFragment", "onActivityCreated()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("DateTimeFragment", "onDestroyView()")
    }
}