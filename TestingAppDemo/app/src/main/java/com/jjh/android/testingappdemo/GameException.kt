package com.jjh.android.testingappdemo

class GameException : RuntimeException {
    constructor(message: String = "", cause: Throwable? = null) : super(message, cause) {}
}