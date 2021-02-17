package com.jjh.android.filedemoapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FILENAME = "datafile.txt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Button handler functions
    fun saveButtonHandler(v: View) {
        saveText()
    }

    fun loadButtonHandler(v: View) {
        loadText()
    }

    fun clearButtonHandlerClick(v: View) {
        clearText()
    }

    private fun saveText() {
        val data = editText.text.toString()
        val charset = Charset.forName("UTF-8")
        try {
            openFileOutput(
                FILENAME,
                Context.MODE_PRIVATE
            ).use { fos -> fos.write(data.toByteArray(charset)) }
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
                editText.setText(contents)
            }
        } catch (exp: IOException) {
            showErrorMessage(exp.message)
        }
    }

    private fun clearText() {
        editText.setText("")
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(this, "Error: $msg", Toast.LENGTH_LONG).show()
    }

}