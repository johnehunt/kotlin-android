package com.jjh.android.asynctaskdemo

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var finalResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val time = findViewById<EditText>(R.id.in_time)
        val button = findViewById<Button>(R.id.btn_run)
        finalResult = findViewById(R.id.tv_result)
        button.setOnClickListener {
            val runner = AsyncTaskRunner()
            val sleepTime = time.getText().toString()
            runner.execute(sleepTime)
        }
    }

    private inner class AsyncTaskRunner :
        AsyncTask<String?, String?, String?>() {

        private var progressDialog: ProgressDialog? = null

        override fun doInBackground(vararg params: String?): String? {
            publishProgress("Sleeping...") // Calls onProgressUpdate()
            return try {
                val time = params[0].let {
                    it!!.toInt() * 1000
                }
                Thread.sleep(time.toLong())
                "Slept for " + params[0] + " seconds"
            } catch (e: Exception) {
                e.printStackTrace()
                e.message
            }
        }

        override fun onPostExecute(result: String?) {
            // execution of result of Long time consuming operation
            progressDialog!!.dismiss()
            finalResult!!.text = result
        }

        override fun onPreExecute() {
            val time = findViewById<EditText>(R.id.in_time)
            progressDialog = ProgressDialog.show(
                this@MainActivity,
                "ProgressDialog",
                "Wait for " + time!!.text.toString() + " seconds"
            )
        }

        override fun onProgressUpdate(vararg values: String?) {
            finalResult!!.text = values[0]
        }

    }
}