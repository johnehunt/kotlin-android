package com.jjh.android.daggerdemo.plain

class Lecturer() {
    val name: String = "John"
    override fun toString(): String {
        return "Lecturer($name)"
    }
}

class Course() {
    val title: String = "OOP for Beginners"
}

class Students() {
    val names = listOf("Denise", "Phoebe", "Adam", "Jasmine")
    override fun toString(): String {
        return "Students($names)"
    }
}

class BasicLesson() {

    val course: Course
    val lecturer: Lecturer
    val students: Students

    init {
        course = Course()
        lecturer = Lecturer()
        students = Students()
    }
    fun presentClass() {
        println("$lecturer takes the class '${course.title}' for $students")
    }

}

class Lesson(val lecturer: Lecturer, val students: Students) {

    lateinit var course: Course

    fun presentClass() {
        println("$lecturer takes the class '${course.title}' for $students")
    }
}
