package com.jjh.android.filedemoapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FILENAME = "datafile.txt"
    }

    private var textView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.editText)
    }

    // Button handler functions
    fun saveButtonHandler(v: View) {
        saveText()
    }

    fun loadButtonHandlerk(v: View) {
        loadText()
    }

    fun clearButtonHandlerClick(v: View) {
        clearText()
    }

    private fun saveText() {
        val data: CharSequence = textView!!.text
        val charset = Charset.forName("UTF-8")
        try {
            openFileOutput(
                FILENAME,
                Context.MODE_PRIVATE
            ).use { fos -> fos.write(data.toString().toByteArray(charset)) }
        } catch (exp: IOException) {
            showErrorMessage(exp.message)
        }
    }

    private fun loadText() {
        try {
            val fis = openFileInput(FILENAME)
            val inputStreamReader =
                InputStreamReader(fis, StandardCharsets.UTF_8)
            val stringBuilder = StringBuilder()
            try {
                BufferedReader(inputStreamReader).use { reader ->
                    var line = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line)
                        line = reader.readLine()
                    }
                }
            } catch (exp: IOException) {
                showErrorMessage(exp.message)
            } finally {
                val contents = stringBuilder.toString()
                textView!!.setText(contents)
            }
        } catch (exp: IOException) {
            showErrorMessage(exp.message)
        }
    }

    private fun clearText() {
        textView!!.setText("")
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(this, "Error: $msg", Toast.LENGTH_LONG).show()
    }

}