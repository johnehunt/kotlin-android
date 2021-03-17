package com.jjh.android.daggerdemo.dagger.services

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

class ShoppingService(val serverUrl: String) {
    fun doSomething() {
        println("ShoppingService server Url $serverUrl")
    }
}

@Module
class ShoppingServiceModule {

    @Provides
    @Singleton
    fun shoppingServiceProvider(@Named("serverURL") serverUrl: String) = ShoppingService(serverUrl)

    @Provides
    @Named("serverURL")
    fun serverUrlProvider(): String = "http://localhost:8080:/api/shopping"
}

