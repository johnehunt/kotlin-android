package com.jjh.android.tictactoe


class Counter(initialLabel: String) {

    private val label: String

    override fun toString(): String {
        return label
    }

    companion object {
        private const val XLabel = "X"
        private const val OLabel = "O"
        val X = Counter(XLabel)
        val O = Counter(OLabel)
    }

    init {
        if (initialLabel == XLabel || initialLabel == OLabel) {
            this.label = initialLabel
        } else {
            throw GameException("Counter Label must be $XLabel or $OLabel")
        }
    }
}