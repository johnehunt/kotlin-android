package com.jjh.android.daggerdemo.plain

fun main() {

    // Externalising the dependencies for Lesson
    val lecturer = Lecturer()
    val students = Students()
    val course = Course()

    val lesson = Lesson(lecturer, students)
    lesson.course = course

    lesson.presentClass()

}