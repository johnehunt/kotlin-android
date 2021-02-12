package com.jjh.android.navigationdrawerdemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        // Set up link to toolbar view
        setSupportActionBar(toolbar)

        // Set up floating action button to display Snackbar dialog
        floating_action_button.setOnClickListener { view ->
            Snackbar.make(view, "Hi there", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }

        // Set up navigation elements
        // Note Action Bar and App Bar as the same thing
        val navController = findNavController(R.id.navigation_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_menu_item,
                                                        R.id.gallery_menu_item,
                                                        R.id.slideshow_menu_item),
                                                  drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items standard options menu
        // on the app bar
        Log.d(TAG, "onCreateOptionsMenu()")
        menuInflater.inflate(R.menu.std_options_menu, menu)
        return true
    }

    // This method is called whenever the user chooses to navigate Up within the
    // application's activity hierarchy from the action bar.
    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "onSupportNavigateUp()")
        val navController = findNavController(R.id.navigation_fragment)
        return navController.navigateUp(appBarConfiguration)
    }
}