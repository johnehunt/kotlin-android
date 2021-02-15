package com.jjh.android.restfulappdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jjh.android.restfulappdemo.model.Driver
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        // private const val URL = "http://10.0.2.2:8080/drivers"
        private const val URL = "http://ergast.com/api/f1/2020/drivers.json"
        private val GSON = Gson()
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
        GetRequestAsyncTask().execute()
    }

    inner class GetRequestAsyncTask: AsyncTask<Void, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "GetRequestAsyncTask.onPreExecute")
        }

        override fun doInBackground(vararg params: Void?): String {
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
                Toast.makeText(this@MainActivity,
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
                editText.setText(this)

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

