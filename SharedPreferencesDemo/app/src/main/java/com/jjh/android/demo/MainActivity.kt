package com.jjh.android.demo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREF_FILE = "my_shared_prefs"
        private const val NAME_KEY = "nameKey"
        private const val EMAIL_KEY = "emailKey"
    }

    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedpreferences = getSharedPreferences(PREF_FILE,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NAME_KEY)) {
            (nameEditText as TextView).text = sharedpreferences.getString(NAME_KEY, "")
        }
        if (sharedpreferences.contains(EMAIL_KEY)) {
            (emailEditText as TextView).text = sharedpreferences.getString(EMAIL_KEY, "")
        }
    }

    fun saveHandler(view: View) {
        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val editor = sharedpreferences.edit()
        editor.putString(NAME_KEY, name)
        editor.putString(EMAIL_KEY, email)
        editor.commit()
    }

    fun clearHandler(view: View) {
        (nameEditText as TextView).text = ""
        (emailEditText as TextView).text = ""
    }

    fun getHandler(view: View) {
        sharedpreferences = getSharedPreferences(PREF_FILE,
                MODE_PRIVATE)
        if (sharedpreferences.contains(NAME_KEY)) {
            (nameEditText as TextView).text = sharedpreferences.getString(NAME_KEY, "")
        }
        if (sharedpreferences.contains(EMAIL_KEY)) {
            (emailEditText as TextView).text = sharedpreferences.getString(EMAIL_KEY, "")
        }
    }

}