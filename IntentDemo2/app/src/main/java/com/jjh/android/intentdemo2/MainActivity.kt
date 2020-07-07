package com.jjh.android.intentdemo2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 222
    }

    private var label: TextView? = null
    private var text: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        label = findViewById(R.id.label)
        text = findViewById(R.id.editText)
    }

    fun onPickContactClick(v: View?) {
        // Tell it that its requestCode (nickname) is 222
        val myData = text!!.text.toString()
        val intent = Intent(Intent.ACTION_PICK, Uri.parse(myData))
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
                val selectedContact = data!!.dataString
                // it will return an URI that looks like:
                // content://contacts/people/n where n is the selected contacts' ID
                label!!.text = selectedContact
            } else {
                // user pressed the BACK button
                label!!.text = "Selection CANCELLED $returnedRequestCode, $resultCode"
            }
        }
    }

}