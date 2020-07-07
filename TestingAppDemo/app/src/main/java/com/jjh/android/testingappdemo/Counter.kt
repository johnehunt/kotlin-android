package com.jjh.android.testingappdemo

class Counter(label: String) {

    lateinit var label: String

    override fun toString(): String {
        return label!!
    }

    companion object {
        const val X = "X"
        const val Y = "O"
    }

    init {
        if (label == X || label == Y) {
            this.label = label
        } else throw GameException("Counter Label must be $X or $Y was $label")
    }
}