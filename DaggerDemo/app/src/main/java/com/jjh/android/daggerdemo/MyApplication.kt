package com.jjh.android.daggerdemo

import android.app.Application

open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by
    // the Activity
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

}