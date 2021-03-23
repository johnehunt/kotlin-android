package com.example.mapflightsdemo.ui.notifications

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapflightsdemo.R
import com.example.mapflightsdemo.databinding.FragmentNotificationsBinding
import com.example.mapflightsdemo.service.BoundFlightService

class NotificationsFragment : Fragment() {

    companion object {
        private const val TAG = "NotificationsFragment"
    }

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null


    private lateinit var connection: ServiceConnection
    private lateinit var service: BoundFlightService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        Log.d(TAG, "Setting up view model")
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        Log.d(TAG, "Setting up binding")
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d(TAG, "Setting up textview observer")
        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        Log.d(TAG, "Setting up button")
        binding.button.setOnClickListener{
            notificationsViewModel.getFlightDetails()
        }
        binding.button.isEnabled = false

        Log.d(TAG, "Launching service")
        val intent = Intent(requireContext(), BoundFlightService::class.java)
        connection = ServiceConnectionHandler().apply {
            requireActivity().bindService(intent, this, Context.BIND_AUTO_CREATE)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class ServiceConnectionHandler : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, binder: IBinder) {
            Log.d(TAG, "ServiceConnectionHandler.onServiceConnected()")
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val demoBinder = binder as BoundFlightService.FlightServiceBinder
            service = demoBinder.service
            notificationsViewModel.flightService = service
            Log.d(TAG, "ServiceConnectionHandler - service bound")
            binding.button.isEnabled = true
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

}