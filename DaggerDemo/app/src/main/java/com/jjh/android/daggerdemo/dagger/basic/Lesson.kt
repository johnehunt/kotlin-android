package com.jjh.android.daggerdemo.dagger.basic

import javax.inject.Inject

class Lecturer @Inject constructor() {
    val name: String = "John"
    override fun toString(): String {
        return "Lecturer($name)"
    }
}

class Course @Inject constructor() {
    val title: String = "OOP for Beginners"
}

class Students @Inject constructor() {
    val names = listOf("Denise", "Phoebe", "Adam", "Jasmine")
    override fun toString(): String {
        return "Students($names)"
    }
}

class Lesson @Inject constructor(val lecturer: Lecturer, val students: Students) {

    @Inject lateinit var course: Course

    fun presentClass() {
        println("$lecturer takes the class '${course.title}' for $students")
    }

}