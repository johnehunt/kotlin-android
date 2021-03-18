package com.jjh.android.restfulappdemo.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jjh.android.restfulappdemo.databinding.DriversFragmentBinding
import com.jjh.android.restfulappdemo.model.Driver
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class DriversFragment : Fragment() {

    companion object {
        private const val TAG = "DriversFragment"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        // private const val URL = "http://10.0.2.2:8080/drivers"
        private const val URL = "http://ergast.com/api/f1/2020/drivers.json"
        private val GSON = Gson()
    }

    private lateinit var binding: DriversFragmentBinding

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
        GetRequestAsyncTask().execute()
    }

    inner class GetRequestAsyncTask: AsyncTask<Unit, Unit, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "GetRequestAsyncTask.onPreExecute")
        }

        override fun doInBackground(vararg params: Unit): String {
            Log.d(TAG, "GetRequestAsyncTask.doInBackground")
            val client = OkHttpClient()

            val request: Request = Request.Builder()
                .url(URL)
                .get()
                .build()

            return try {
                val response: Response = client.newCall(request).execute()
                val body = response.body?.string()
                body ?: ""
            } catch (e: Exception) {
                Toast.makeText(requireContext(),
                    e.localizedMessage,
                    Toast.LENGTH_LONG).show()
                ""
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(TAG, "GetRequestAsyncTask.onPostExecute")
            Log.d(TAG, result)

            val startIndex = result.indexOf("\"Drivers\":") + "\"Drivers\":".length
            val driversJson = result.slice(IntRange(startIndex, result.length - 4))
            Log.d(TAG, driversJson)

            driversJson.run {
                binding.editText.setText(this)

                // Example of converting JSON to an object
                val drivers: List<Driver> =
                    GSON.fromJson(this,
                        Array<Driver>::class.java)
                        .toList()

                Log.d(TAG, drivers.toString())

                // And an object to JSON
                val jsonString = GSON.toJson(drivers)
                Log.d(TAG, jsonString)

            }
        }

    }

}