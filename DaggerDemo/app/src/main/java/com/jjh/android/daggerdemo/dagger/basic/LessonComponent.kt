package com.jjh.android.daggerdemo.dagger.basic

import dagger.Component

@Component
interface LessonComponent {
    fun getLesson(): Lesson
}