package com.jjh.android.asynctaskdemo

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClick(v: View) {
        val runner = AsyncTaskRunner()
        val sleepTime = inTime.text.toString()
        runner.execute(sleepTime)
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
            finalResult.text = result
        }

        override fun onPreExecute() {
            val time = findViewById<EditText>(R.id.inTime)
            progressDialog = ProgressDialog.show(
                this@MainActivity,
                "ProgressDialog",
                "Wait for " + time!!.text.toString() + " seconds"
            )
        }

        override fun onProgressUpdate(vararg values: String?) {
            finalResult.text = values[0]
        }

    }
}