package com.jjh.android.mapstreetviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private val LONDON = LatLng(51.5074, 0.1278)
        private const val TAG = "MapsActivity"
    }
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        setContentView(R.layout.activity_maps)

        // Load up StreetView Fragment
        val streetViewPanoramaFragment =
                supportFragmentManager.findFragmentById(R.id.streetviewpanorama) as SupportStreetViewPanoramaFragment?

        // Set the street view panorama to London location
        streetViewPanoramaFragment?.getStreetViewPanoramaAsync { panorama ->
            // Only set the panorama to LONDON on startup (when no panoramas have been
            // loaded which is when the savedInstanceState is null).
            savedInstanceState ?: panorama.setPosition(LONDON)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady()")
    }
}