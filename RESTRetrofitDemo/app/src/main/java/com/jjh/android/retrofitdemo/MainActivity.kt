package com.jjh.android.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.jjh.android.retrofitdemo.model.Drivers
import com.jjh.android.retrofitdemo.service.DriversService

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        // private const val URL = "http://10.0.2.2:8080/"
        private const val URL = "http://ergast.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
        textView.text = URL
    }

    fun onClick(view: View) {
        Log.d(TAG, "onClick")
        val b: Button = view as Button
        b.isClickable = false
        getDrivers()
    }

    private fun getDrivers() {
        Log.d(TAG, "getDrivers")

        Log.d(TAG, "getDrivers - building Retrofit object")
        val httpClient = OkHttpClient()
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

        Log.d(TAG, "getDrivers - building DriversService")
        val service: DriversService = retrofit.create(DriversService::class.java)

        Log.d(TAG, "getDrivers - creating the service call")
        val serviceCall: Call<Drivers> = service.getDrivers()

        Log.d(TAG, "getDrivers - invoke service call asynchronously")
        serviceCall.enqueue(object : Callback<Drivers> {

            override fun onResponse(call: Call<Drivers>, response: Response<Drivers>) {
                Log.d(TAG, "onResponse")
                Log.d(TAG, "response code: ${response.code()}")
                val body: Drivers? = response.body()
                body?.apply {
                    editText.setText(this.toString())
                }
            }

            override fun onFailure(call: Call<Drivers>, throwable: Throwable?) {
                Log.d(TAG, "onFailure")
                Log.d(TAG, throwable!!.message)
            }
        })
    }
}