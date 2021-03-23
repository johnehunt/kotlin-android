package com.example.mapflightsdemo.ui.dashboard

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapflightsdemo.R
import com.example.mapflightsdemo.databinding.FragmentDashboardBinding
import com.example.mapflightsdemo.service.BoundFlightService
import com.example.mapflightsdemo.ui.notifications.NotificationsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class DashboardFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    companion object {
        private const val TAG = "DashboardFragment"
    }

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var map: GoogleMap

    private lateinit var connection: ServiceConnection
    private lateinit var service: BoundFlightService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        Log.d(TAG, "onCreateView()")

        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragmentContainerView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        dashboardViewModel.latLong.observe(viewLifecycleOwner, Observer {
            changeLatLong(it)
        })

        binding.flightButton.setOnClickListener {
            dashboardViewModel.setFlightDetails()
        }
        binding.flightButton.isEnabled = false

        Log.d(TAG, "Launching service")
        val intent = Intent(requireContext(), BoundFlightService::class.java)
        connection = ServiceConnectionHandler().apply {
            requireActivity().bindService(intent, this, Context.BIND_AUTO_CREATE)
        }

        return root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView()")

        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady()")

        map = googleMap

        // Set the Map Type
        //map.mapType = GoogleMap.MAP_TYPE_SATELLITE

        // Turn on Zoom controls
        map.uiSettings.isZoomControlsEnabled = true
        // Turn on Compass
        map.uiSettings.isCompassEnabled = true

        // Set up a listener for marker clicks
        map.setOnMarkerClickListener(this)

    }

    private fun changeLatLong(latAndLong: LatLng) {
        map.addMarker(
            MarkerOptions()
                .position(latAndLong)
                .title("FLight")
                .snippet("live flight info")
                .icon(
                    BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)))

        // Changing the Map position and zoom
        val cameraPosition = CameraPosition.Builder().target(latAndLong).zoom(9f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.d(TAG, "onMarkerClick()")
        // Retrieve the data from the marker.
        val clickCount = marker.tag as Int?

        // Check if a click count was set, then display the click count
        clickCount?.also{
            val clicks = it + 1
            marker.tag = clicks
            Toast.makeText(
                requireActivity(),
                "${marker.title} has been clicked $clicks times",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }

    private inner class ServiceConnectionHandler : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, binder: IBinder) {
            Log.d(TAG, "ServiceConnectionHandler.onServiceConnected()")
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val demoBinder = binder as BoundFlightService.FlightServiceBinder
            service = demoBinder.service
            dashboardViewModel.flightService = service
            Log.d(TAG, "ServiceConnectionHandler - service bound")
            binding.flightButton.isEnabled = true
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

}