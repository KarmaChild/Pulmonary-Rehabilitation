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
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DashboardActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun dashboardActivityInstrumentationTest() {

        val imageView = onView(
            allOf(withId(R.id.view), isDisplayed())
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(withId(R.id.widget2), isDisplayed())
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(withId(R.id.greenBackground), isDisplayed())
        )
        imageView3.check(matches(isDisplayed()))
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
