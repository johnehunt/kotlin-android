package com.jjh.android.daggerdemo

import android.content.Context
import android.util.Log
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val userManager: UserManager) {
    fun logMe() {
        Log.d("DaggerDemo", "RegistrationViewModel.logMe")
        userManager.logMe()
    }
}

class UserManager @Inject constructor(val context: Context) {
    fun logMe() {
        Log.d("DaggerDemo", "UserManager: ${context.toString()}")
        Log.d("DaggerDemo", "UserManager.logMe")
    }
}

// Definition of a Dagger component that adds info from the StorageModule to the graph
@Component
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
}


