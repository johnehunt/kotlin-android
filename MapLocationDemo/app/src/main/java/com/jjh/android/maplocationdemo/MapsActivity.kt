package com.jjh.android.maplocationdemo

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMyLocationClickListener, OnMyLocationButtonClickListener, OnRequestPermissionsResultCallback {

    companion object {
        private const val TAG = "MapsActivity"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Gte last known location
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        Log.d(TAG, "Last Known location $location")
                    }

            // Set up location call back
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return
                    for (location in locationResult.locations) {
                        Log.d(TAG, "Location Update $location")
                    }
                }
            }

            // Set up a location Request object
            val locationRequest = LocationRequest.create()?.apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            // register for location updates
            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    Looper.getMainLooper())

            // To un register from location updates
            // fusedLocationClient.removeLocationUpdates(locationCallback)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady()")
        map = googleMap

        // Enables the My Location layer if the fine location permission has been granted.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            // Location permission has not been granted yet, request it.
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        // Set the My Location layer and the My Location button
        map.isMyLocationEnabled = true

        // Register activity for location callbacks
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)

        // Programmatically obtaining the current location


    }

    // Call back member functions for Location listener interfaces
    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
    }
}