package com.jjh.android.restfulappdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jjh.android.restfulappdemo.model.Person
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
        private const val URL = "http://10.0.2.2:8080/users"
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
                e.localizedMessage
            }
        }

        override fun onPostExecute(result: String) {
            Log.d(TAG, "GetRequestAsyncTask.onPostExecute")
            super.onPostExecute(result)
            result.apply {
                editText.setText(this)

                // Example fo converting JSON to an object
                val persons: List<Person> =
                    GSON.fromJson(
                    this,
                        Array<Person>::class.java).toList()

                Log.d(TAG, persons.toString())

                // And an object to JSON
                val jsonString = GSON.toJson(persons)
                Log.d(TAG, jsonString)

            }
        }

    }
}

