package com.jjh.android.testingappdemo

import org.junit.*
import org.junit.Assert.*


class CalculatorTest {

    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun test_one_increment_operation() {
        calculator.increment()
        assertEquals("counter should be 1", 1, calculator.count)
    }

    @Test
    fun test_two_increment_operations() {
        calculator.increment()
        calculator.increment()
        assertEquals("counter should be 2", 2, calculator.count)
    }

    @Test
    fun test_one_decrement_operation() {
        calculator.decrement()
        assertEquals("counter should be -1", -1, calculator.count)
    }

}