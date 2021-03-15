package com.jjh.android.mapdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener {

    companion object {
        private const val TAG = "MapsActivity"
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady()")

        map = googleMap

        // Set the Map Type
        //map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        
        // Turn on Zoom controls
        map.uiSettings.isZoomControlsEnabled = true
        // Turn on Compass
        map.uiSettings.isCompassEnabled = true

        // Add a marker in Sydney and move the camera
        //val latAndLong = LatLng(-33.852, 151.211)
        val latAndLong = LatLng(51.5074, 0.1278)
        val marker = map.addMarker(
            MarkerOptions()
                .position(latAndLong)
                .title("Marker in London")
                .snippet("Capital City of UK")
                .icon(
                    BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)
                )
        )

        // Changing the Map position and zoom
        val cameraPosition = CameraPosition.Builder().target(latAndLong).zoom(9f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
//        val cameraPosition = CameraPosition.Builder()
//            .target(MOUNTAIN_VIEW) // Sets the center of the map to Mountain View
//            .zoom(17f) // Sets the zoom
//            .bearing(90f) // Sets the orientation of the camera to east
//            .tilt(30f) // Sets the tilt of the camera to 30 degrees
//            .build() // Creates a CameraPosition from the builder

        // Set up some data to use with the marker
        marker.tag = 0
        // Set up a listener for marker clicks
        map.setOnMarkerClickListener(this)

    }

    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {
        Log.d(TAG, "onMarkerClick()")
        // Retrieve the data from the marker.
        val clickCount = marker.tag as Int?

        // Check if a click count was set, then display the click count
        clickCount?.apply{
            val clicks = this + 1
            marker.tag = clicks
            Toast.makeText(
                this@MapsActivity,
                "${marker.title} has been clicked $clicks times",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }

}