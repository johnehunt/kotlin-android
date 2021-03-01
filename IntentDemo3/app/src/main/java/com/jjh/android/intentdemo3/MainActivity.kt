package com.jjh.android.intentdemo3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickInvokeSubActivity(v: View) {
        try {
            // create an Intent to talk to Sub-Activity
            val intent = Intent(this, SubActivity::class.java)
            // Bundle allows multiple data items to be sent simply
            val bundle = Bundle()
            bundle.putString("msg", textView.text.toString())
            // bind the Bundle and the Intent that talks to Activity2
            intent.putExtras(bundle)
            // call Activity2 and wait for results
            startActivityForResult(intent, REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(baseContext, e.message,
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(returnedRequestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(returnedRequestCode, resultCode, data)
        try {
            // check that these results are for me
            if (REQUEST_CODE == returnedRequestCode) {
                // SubActivity is over - see what happened
                if (resultCode == Activity.RESULT_OK) {
                    val bundle = data?.extras
                    val result = bundle?.getString("result") ?: "No Result"
                    resultTextView.text = result
                } else {
                    // user pressed the BACK button
                    textView.text = "Selection CANCELLED!"
                }
            }
        } catch (e: Exception) {
            Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

}