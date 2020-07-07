package com.jjh.android.testingappdemo

import org.hamcrest.CoreMatchers.equalTo
import org.junit.*
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {
        @BeforeClass
        @JvmStatic
        internal fun beforeAll() {
            println("BeforeAll called")
        }
        @AfterClass
        @JvmStatic
        internal fun afterAll() {
            println("AfterAll called")
        }
    }

    @Before
    fun setUp() {
        println("Before called (setup)")
    }

    @After
    fun tearDown() {
        println("After called (tearDown")
    }

    @Test
    fun test_one_plus_one_operation()  {
        val result = 1 + 1
        assertEquals(2, result)
    }

    @Test
    fun test_two_minus_one_operation()  {
        val result = 2 - 1
        assertEquals("2 -1 should be 1", 1, result)
    }

    @Test
    @Ignore("Test is ignored as a demonstration")
    fun test_one_increment_and_one_decrement_operations()  {
        val result = 2 - 1
        assertEquals("2 -1 should be 1", 1, result)
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun test_division_operation() {
         throw RuntimeException("oops")
    }

    @Test(timeout = 1000)
    fun test_long_calculation() {
        println("long calculation")
        Thread.sleep(1001)
    }

    @Test
    fun strings_are_equals() {
        val s1 = "Ginger Biscuit"
        val s2 = "Choclate Biscuit"
        assertThat(s1, equalTo(s2))
    }

}

