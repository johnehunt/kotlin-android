package com.jjh.android.restfulappdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        private const val URL = "http://10.0.2.2:8080/users"
    }

    private lateinit var textView: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView.text = URL

        editText = findViewById(R.id.editText)
    }

    fun onClick(view: View) {
        val b: Button = view as Button
        b.isClickable = false
        GetRequestAsyncTask().execute()
    }

    inner class GetRequestAsyncTask: AsyncTask<Void, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute")
        }

        override fun doInBackground(vararg params: Void?): String {
            Log.d(TAG, "doInBackground")
            val client = OkHttpClient()

            val request: Request = Request.Builder()
                .url(URL)
                .get()
                .build()

            return try {
                val response: Response = client.newCall(request).execute()
                val body = response.body?.string() + ""
                body
            } catch (e: Exception) {
                e.localizedMessage
            }
        }

        override fun onPostExecute(result: String?) {
            Log.d(TAG, "onPostExecute")
            super.onPostExecute(result)
            result?.apply {
                editText.setText(this)
            }
        }

    }
}

