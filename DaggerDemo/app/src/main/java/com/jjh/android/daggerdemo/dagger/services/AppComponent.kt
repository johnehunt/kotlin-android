package com.jjh.android.daggerdemo.dagger.services

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [  // Where we will find the types to inject
    UserServiceModule::class,
    ShoppingServiceModule::class])
interface AppComponent {
  fun inject(main: Main)  // Says what we will inject into
}
