package com.jjh.android.intentdemo3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sub_activity.*

class SubActivity : AppCompatActivity() {

    // Hold data sent by parent
    private var data: String? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sub_activity)

        // Retrieve data sent by parent
        val myBundle: Bundle? = intent.extras
        // Extract the individual data parts of the bundle
        data = myBundle?.getString("msg")
    }

    fun onClickSendResponse(v: View?) {
        finish()
    }

    override fun finish() {
        val intent = Intent()
        intent.putExtra("result", data + " " + editText.text)
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}