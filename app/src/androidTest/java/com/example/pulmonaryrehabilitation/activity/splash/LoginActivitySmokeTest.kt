package com.example.pulmonaryrehabilitation.activity.splash

import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.login.EmailLoginActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.QuestionnaireActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivitySmokeTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(EmailLoginActivity::class.java)

    @Test
    fun splashActivityTest2() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.emailEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("smoke@gmail.com"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.passwordEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("smoketest"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.LogIn), withText("Log In"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        ActivityScenario.launch(QuestionnaireActivity::class.java)

        val button = onView(
            allOf(withId(R.id.submit_answer), withText("SUBMIT"), isDisplayed())
        )
        button.check(matches(isDisplayed()))

        val radioButton = onView(
            allOf(withId(R.id.terrible), withText("Terrible"), isDisplayed())
        )
        radioButton.check(matches(isDisplayed()))
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
