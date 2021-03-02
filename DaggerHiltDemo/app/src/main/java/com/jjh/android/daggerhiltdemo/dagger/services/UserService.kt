package com.jjh.android.daggerhiltdemo.dagger.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

class UserService {
    fun doSomething() {
        println("UserService server")
    }
}

@Module
@InstallIn(ActivityComponent::class)
class UserServiceModule {
    @Provides
    fun userServiceProvider() = UserService()
}

