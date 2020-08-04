package com.jjh.android.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jjh.android.retrofitdemo.model.Person
import com.jjh.android.retrofitdemo.service.PersonService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        // Note 10.0.22 is mapped to the localhost of the host machine
        // as the Emulator is running a Virtual Machine where localhost
        // is the mobile device
        private const val URL = "http://10.0.2.2:8080/"
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
        getUsers()
    }

    private fun getUsers() {
        Log.d(TAG, "getUsers")

        Log.d(TAG, "getUsers - building Retrofit object")
        val httpClient = OkHttpClient()
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

        Log.d(TAG, "getUsers - building PersonService")
        val service: PersonService = retrofit.create(PersonService::class.java)

        Log.d(TAG, "getUsers - creating the service call")
        val serviceCall: Call<List<Person>> = service.getUsers()

        Log.d(TAG, "getUsers - invoke service call asynchronously")
        serviceCall.enqueue(object : Callback<List<Person>> {

            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                Log.d(TAG, "onResponse")
                val persons: List<Person>? = response.body()
                persons?.apply {
                    editText.setText(this.toString())
                }
            }

            override fun onFailure(call: Call<List<Person>>, throwable: Throwable?) {
                Log.d(TAG, "onFailure")
                Log.d(TAG, throwable?.message)
            }
        })
    }
}