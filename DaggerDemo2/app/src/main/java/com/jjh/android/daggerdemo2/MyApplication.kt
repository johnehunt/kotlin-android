package com.jjh.android.daggerdemo2

import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication() {

  // Made appComponent private
  private val appComponent: AppComponent by lazy {
    // Creates an instance of AppComponent using its Factory constructor
    // We pass the applicationContext that will be used as Context in the graph
    DaggerAppComponent.factory().create(applicationContext)
  }

    override fun applicationInjector() = appComponent
  }
