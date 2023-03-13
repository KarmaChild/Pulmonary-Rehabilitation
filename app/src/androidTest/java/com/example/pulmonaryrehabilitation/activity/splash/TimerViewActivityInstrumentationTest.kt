package com.example.pulmonaryrehabilitation.activity.splash

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.exerciseplayeractivity.ExercisePlayerTimerViewActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TimerViewActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(ExercisePlayerTimerViewActivity::class.java)

    @Test
    fun timerViewActivityInstrumentationTest() {

        val textView = onView(
            allOf(withId(R.id.timerViewExerciseName), withText("Test Exercise 1"), isDisplayed())
        )
        textView.check(matches(isDisplayed()))

        val videoView = onView(
            allOf(withId(R.id.timerVideoView), isDisplayed())
        )
        videoView.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(withId(R.id.stepTitleLabel), withText("Test Step 1"), isDisplayed())
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(withId(R.id.stepDescriptionLabel), withText("Do thing x"), isDisplayed())
        )
        textView4.check(matches(isDisplayed()))

        val progressBar = onView(
            allOf(withId(R.id.timerProgressBar), isDisplayed())
        )
        progressBar.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
