package com.jjh.android.daggerdemo.dagger.customscope

import dagger.Component
import javax.inject.Inject

@FragmentScope
class UserRepository @Inject constructor()

@FragmentScope
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