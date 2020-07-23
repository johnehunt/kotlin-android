package com.jjh.android.daggerdemo.dagger.basic

fun main(args: Array<String>) {

    val component: LessonComponent = DaggerLessonComponent.create()
    val lesson = component.getLesson()
    lesson.presentClass()

}