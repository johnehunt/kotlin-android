package com.jjh.android.daggerdemo.dagger.singletons

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfile @Inject constructor() {
}

@Singleton
@Component
interface UserProfileComponent {
    fun getUserProfile(): UserProfile
}

fun main(args: Array<String>) {
    val component: UserProfileComponent = DaggerUserProfileComponent.create()
    val profile1 = component.getUserProfile()
    val profile2 = component.getUserProfile()

    println("profile1 == profile2: ${profile1 == profile2}")
}