package com.jjh.android.tictactoe

class GameException(message: String = "", cause: Throwable? = null) :
    RuntimeException(message, cause)