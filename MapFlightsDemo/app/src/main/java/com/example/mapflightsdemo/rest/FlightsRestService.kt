package com.example.mapflightsdemo.rest

import com.example.mapflightsdemo.domain.FlightDetails
import retrofit2.Call
import retrofit2.http.GET

interface FlightsRestService {
    @GET("/v1/flights?access_key=3947ae8677642b0c8851397a6b067934")
    fun getFlightDetails(): Call<FlightDetails>
}