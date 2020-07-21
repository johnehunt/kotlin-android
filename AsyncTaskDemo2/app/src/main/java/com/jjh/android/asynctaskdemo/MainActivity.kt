package com.jjh.android.asynctaskdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var time: EditText
    private lateinit var finalResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main)
        time = findViewById(R.id.in_time)
        finalResult = findViewById(R.id.tv_result)

        val button: Button = findViewById(R.id.btn_run)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick")
                val runner = AsyncTaskRunner()
                val sleepTime = time.text.toString()
                runner.execute(sleepTime)
            }
        })
    }

    inner class AsyncTaskRunner : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String? {
            Log.d(TAG, "AsyncTaskRunner.doInBackground")
            return try {
                val time = params[0].toInt() * 1000
                Thread.sleep(time.toLong())
                "Slept for " + params[0] + " seconds"
            } catch (e: Exception) {
                e.printStackTrace()
                e.message + ""
            }
        }

        override fun onPostExecute(result: String?) {
            Log.d(TAG, "AsyncTaskRunner.onPostExecute")
            // result of Long time consuming operation
            finalResult.text = result
        }

        override fun onPreExecute() {
            Log.d(TAG, "AsyncTaskRunner.onPreExecute")
            Toast.makeText(applicationContext,
                    "Wait for " + time.text.toString() + " seconds",
                    Toast.LENGTH_SHORT).show();
        }

    }
}