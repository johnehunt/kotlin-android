package com.jjh.android.daggerdemo2

import android.content.Context
import com.jjh.android.daggerdemo2.dagger.services.ShoppingServiceModule
import com.jjh.android.daggerdemo2.dagger.services.UserServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module
abstract class ActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
}

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        UserServiceModule::class,
        ShoppingServiceModule::class]
)
interface AppComponent: AndroidInjector<MyApplication> {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can have dependencies injected by this Component
    fun inject(activity: MainActivity)

}


