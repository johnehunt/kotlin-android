package com.jjh.android.daggerdemo2.dagger.services

import com.jjh.android.daggerdemo2.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
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

