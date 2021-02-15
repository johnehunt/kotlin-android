package com.jjh.android.daggerdemo.dagger.customscope

import dagger.Component
import javax.inject.Inject

@MyCustomScope
class UserRepository @Inject constructor()

@MyCustomScope
@Component
interface UserRepositoryComponent {
    fun getUserRepository(): UserRepository
}

fun main(args: Array<String>) {
    val component: UserRepositoryComponent =
        DaggerUserRepositoryComponent.create()
    val repo1 = component.getUserRepository()
    val repo2 = component.getUserRepository()

    println("repo1 == repo2: ${repo1 == repo2}")

}