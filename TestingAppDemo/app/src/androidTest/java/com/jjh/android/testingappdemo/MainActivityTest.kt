package com.jjh.android.testingappdemo

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testActivityState() {
        val state = activityRule.scenario.state
        Assert.assertEquals("activity state should be", Lifecycle.State.RESUMED, state)
    }

    @Test
    fun testClickTopRightButtonUpdatedWithPlayerCounter() {
        currentActivity.run {
            val player = this.board.humanPlayer
            val counter = player.counter
            Espresso.onView(ViewMatchers.withId(R.id.button0)).perform(ViewActions.click())
            // Check the screen update
            Espresso.onView(ViewMatchers.withId(R.id.button0))
                .check(ViewAssertions.matches(withText(counter.label)))
        }
    }

    @Test
    fun testClickResetButtonIsThenDisabled() {
        Espresso.onView(ViewMatchers.withId(R.id.button9))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())))
    }

    private val currentActivity: MainActivity
        get() {
            var activity: MainActivity? = null
            activityRule.scenario.onActivity {
                activity = it
            }
            return activity!!
        }
}