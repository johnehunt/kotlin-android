package com.jjh.android.retrofitdemo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.jjh.android.retrofitdemo.databinding.DriversFragmentBinding
import com.jjh.android.retrofitdemo.model.Drivers
import com.jjh.android.retrofitdemo.service.DriversService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriversFragment : Fragment() {

    companion object {
        private const val TAG = "DriversFragment"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        // private const val URL = "http://10.0.2.2:8080/"
        private const val URL = "http://ergast.com/"
    }

    private lateinit var binding: DriversFragmentBinding

    private val viewModel by viewModels<DriversViewModel>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DriversFragmentBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener{onButtonClick()}
        binding.textView.text = URL
        return binding.root
    }

    private fun onButtonClick() {
        Log.d(TAG, "onClick")
        binding.button.isClickable = false
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
                    binding.editText.setText(this.toString())
                }
            }

            override fun onFailure(call: Call<Drivers>, throwable: Throwable) {
                Log.d(TAG, "onFailure")
                Log.d(TAG, throwable.message ?: "")
            }
        })
    }

}