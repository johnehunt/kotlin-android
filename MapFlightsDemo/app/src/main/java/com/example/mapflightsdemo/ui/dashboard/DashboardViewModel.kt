package com.example.mapflightsdemo.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapflightsdemo.domain.FlightDetails
import com.example.mapflightsdemo.rest.FlightsRestService
import com.example.mapflightsdemo.service.BoundFlightService
import com.google.android.gms.maps.model.LatLng
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardViewModel : ViewModel() {

    lateinit var flightService: BoundFlightService
    val latLong: MutableLiveData<LatLng> = MutableLiveData<LatLng>()

    fun setFlightDetails() {
        flightService.getFlightDetails{

            val live = it.data[0].live
            if (live != null) {
                val latitude = live.latitude.toDouble()
                val longtitude = live.longitude.toDouble()
                latLong.value = LatLng(latitude, longtitude)
            } else {
                latLong.value = LatLng(51.5074, 0.1278)
            }

        }
    }

}