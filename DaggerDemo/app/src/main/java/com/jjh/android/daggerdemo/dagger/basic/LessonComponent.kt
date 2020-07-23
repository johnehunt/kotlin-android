package com.jjh.android.daggerdemo.dagger.basic

import com.jjh.android.daggerdemo.dagger.basic.Lesson
import dagger.Component

@Component
interface LessonComponent {
    fun getLesson(): Lesson
}