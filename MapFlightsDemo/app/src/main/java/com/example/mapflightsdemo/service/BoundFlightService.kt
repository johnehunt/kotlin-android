package com.example.mapflightsdemo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.mapflightsdemo.domain.FlightDetails
import com.example.mapflightsdemo.rest.FlightsRestService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoundFlightService: Service()  {

    companion object {
        private const val TAG = "BoundFlightService"
        private const val URL = "http://api.aviationstack.com/"
    }

    // Binding support
    private val binder: IBinder = FlightServiceBinder()

    private lateinit var flightDetails: FlightDetails

    // Common pattern for accessing service
    inner class FlightServiceBinder : Binder() {
        val service: BoundFlightService
            get() = this@BoundFlightService
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind()")
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind()")
        return true
    }

    fun getFlightDetails(callback: (FlightDetails) -> Unit) {

        if (!this::flightDetails.isInitialized) {

            synchronized(this) {
                val httpClient = OkHttpClient()

                val retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()

                Log.d(TAG, "getFlightDetails - building FlightsRestService")
                val service: FlightsRestService = retrofit.create(FlightsRestService::class.java)

                Log.d(TAG, "getFlightDetails - creating the service call")
                val serviceCall: Call<FlightDetails> = service.getFlightDetails()

                Log.d(TAG, "getFlightDetails - invoke service call asynchronously")
                serviceCall.enqueue(object : Callback<FlightDetails> {

                    override fun onResponse(
                        call: Call<FlightDetails>,
                        response: Response<FlightDetails>
                    ) {
                        Log.d(TAG, "onResponse")
                        Log.d(TAG, "response code: ${response.code()}")
                        val body: FlightDetails? = response.body()
                        body?.run {
                            Log.d(TAG, this.toString())
                            callback(body)
                        }
                    }

                    override fun onFailure(call: Call<FlightDetails>, throwable: Throwable) {
                        Log.d(TAG, "onFailure")
                        Log.d(TAG, throwable.message ?: "")
                    }
                })
            }
        } else {
            callback(flightDetails)
        }
    }



}