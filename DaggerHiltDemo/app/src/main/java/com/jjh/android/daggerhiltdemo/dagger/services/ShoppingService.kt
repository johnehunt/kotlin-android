package com.jjh.android.daggerhiltdemo.dagger.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

class ShoppingService(@Named("serverURL") val serverUrl: String) {
    fun doSomething() {
        println("ShoppingService server Url $serverUrl")
    }
}

@Module
@InstallIn(ActivityComponent::class)
class ShoppingServiceModule {
    @Provides
    fun shoppingServiceProvider(@Named("serverURL") serverUrl: String) = ShoppingService(serverUrl)

    @Provides
    @Named("serverURL")
    fun serverUrlProvider() = "http://localhost:8080:/api/shopping"
}

