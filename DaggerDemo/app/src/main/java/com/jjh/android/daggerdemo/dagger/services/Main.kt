package com.jjh.android.daggerdemo.dagger.services

import javax.inject.Inject

fun main() {
  println("Starting main() Function")
  val main = Main()
  main.start()
  println("Done")
}

class Main {

  @Inject
  lateinit var shoppingService: ShoppingService

  @Inject
  lateinit var userService: UserService

  init {
    val appComponent: AppComponent = DaggerAppComponent.builder().build()
    appComponent.inject(this)
  }

  fun start() {
    userService.doSomething()
    shoppingService.doSomething()
  }

}
