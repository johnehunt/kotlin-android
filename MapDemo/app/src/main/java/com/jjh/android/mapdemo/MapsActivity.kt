package com.jjh.android.mapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val latAndLong = LatLng(-33.852, 151.211)
        val latAndLong = LatLng(51.5074, 0.1278)
        mMap.addMarker(MarkerOptions()
            .position(latAndLong)
            .title("Marker in London")
            .snippet("Capital City of UK"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latAndLong))
        mMap.animateCamera(CameraUpdateFactory.zoomIn())

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
//        val cameraPosition = CameraPosition.Builder()
//            .target(MOUNTAIN_VIEW) // Sets the center of the map to Mountain View
//            .zoom(17f) // Sets the zoom
//            .bearing(90f) // Sets the orientation of the camera to east
//            .tilt(30f) // Sets the tilt of the camera to 30 degrees
//            .build() // Creates a CameraPosition from the builder


    }
}