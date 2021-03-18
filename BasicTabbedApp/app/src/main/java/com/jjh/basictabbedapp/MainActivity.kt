package com.jjh.basictabbedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjh.basictabbedapp.ui.main.MainFragment

/**
 * Illustrates example of a fixed set of tab items displayed iwhtin a tablayout
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
