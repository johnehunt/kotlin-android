package com.jjh.android.intentdemo1

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var diallerText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize dialier text field field
        diallerText = findViewById(R.id.editText)
    }

    // Handler method for DIal Button
    fun onDialButtonClick(v: View?) {
        val myData = diallerText!!.text.toString()
        // Creates an Intent to trigger dialler
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(myData))
        startActivity(intent)
    }

    // Inner class used to handle search button
    fun onSearchButtonClick(v: View?) {
        // triggers web search activity
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, "MG F Car")
        startActivity(intent)
    }
}