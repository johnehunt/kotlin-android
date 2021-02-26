package com.jjh.android.daggerdemo.dagger.services

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    UserServiceModule::class,
    ShoppingServiceModule::class]
)
interface AppComponent {
  fun inject(main: Main)
}
