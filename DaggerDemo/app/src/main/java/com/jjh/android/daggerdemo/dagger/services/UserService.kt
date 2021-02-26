package com.jjh.android.daggerdemo.dagger.services

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class UserService {
    fun doSomething() {
        println("UserService server")
    }
}

@Module
class UserServiceModule {
    @Provides
    @Singleton
    fun userServiceProvider() = UserService()
}