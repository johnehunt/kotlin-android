package com.jjh.android.daggerdemo.dagger.modules

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

interface Lesson {
    fun presentClass()
}

class OnlineLesson : Lesson {
    override fun presentClass() {
        println("Give Lesson on line")
    }
}

class Programme @Inject constructor(val lesson: Lesson) {
    fun giveLectures() {
        lesson.presentClass()
    }
}

@Module
class LessonModule {
    @Provides
    fun lessonProvider(): Lesson = OnlineLesson()
}

@Component(modules= [LessonModule::class])
interface ProgrammeComponent {
    fun getProgramme(): Programme
}

fun main(args: Array<String>) {
    val component: ProgrammeComponent = DaggerProgrammeComponent.create()
    val programme = component.getProgramme()
    programme.giveLectures()
}

