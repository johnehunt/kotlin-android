package com.jjh.android.daggerdemo

import android.content.Context
import com.jjh.android.daggerdemo.dagger.services.ShoppingServiceModule
import com.jjh.android.daggerdemo.dagger.services.UserServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UserServiceModule::class,
        ShoppingServiceModule::class]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can have dependencies injected by this Component
    fun inject(activity: MainActivity)

}


