package com.jjh.android.intentdemo2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPickContactClick(v: View) {
        val myData = editText.text.toString()
        val intent = Intent(Intent.ACTION_PICK, Uri.parse(myData))
        // Start the activity to generate a result passing in result code
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(returnedRequestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(returnedRequestCode, resultCode, data)
        // use requestCode to find out who is talking to us
        if (returnedRequestCode == REQUEST_CODE) {
            // 222 is our friendly contact-picker activity
            if (resultCode == Activity.RESULT_OK) {
                data?.run{
                    val selectedContact = this.dataString
                    // it will return an URI that looks like:
                    // content://contacts/people/n where n is the selected contacts' ID
                    label.text = selectedContact
                }
            } else {
                // user pressed the BACK button
                label.text = "Selection CANCELLED $returnedRequestCode, $resultCode"
            }
        }
    }

}