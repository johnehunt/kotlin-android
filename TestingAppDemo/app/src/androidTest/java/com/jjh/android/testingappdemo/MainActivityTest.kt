package com.jjh.android.testingappdemo

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun clickTopRightButton() {
        val activityUnderTest = activityRule.activity
        val board = activityUnderTest.board
        val player = board!!.humanPlayer
        val counter = player.counter
        Espresso.onView(ViewMatchers.withId(R.id.button0)).perform(ViewActions.click())
        // Check the screen update
        Espresso.onView(ViewMatchers.withId(R.id.button0))
            .check(ViewAssertions.matches(withText(counter.label)))
    }

    @Test
    fun clickResetButtonIsDisabled() {
        Espresso.onView(ViewMatchers.withId(R.id.button9))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())))
    }
}