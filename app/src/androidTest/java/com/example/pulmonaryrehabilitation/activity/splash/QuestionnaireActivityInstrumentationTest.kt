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
import com.example.pulmonaryrehabilitation.activity.questionnaire.QuestionnaireActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QuestionnaireActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(QuestionnaireActivity::class.java)

    @Test
    fun questionnaireActivityInstrumentationTest() {

        val materialRadioButton = onView(
            allOf(
                withId(R.id.terrible), withText("Terrible"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialRadioButton.perform(click())

        val materialRadioButton2 = onView(
            allOf(
                withId(R.id.bad), withText("Bad"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialRadioButton2.perform(click())

        val materialRadioButton3 = onView(
            allOf(
                withId(R.id.ok), withText("OK"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialRadioButton3.perform(click())

        val materialRadioButton4 = onView(
            allOf(
                withId(R.id.good), withText("Good"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialRadioButton4.perform(click())

        val materialRadioButton5 = onView(
            allOf(
                withId(R.id.great), withText("Great"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialRadioButton5.perform(click())

        val materialRadioButton6 = onView(
            allOf(
                withId(R.id.terrible), withText("Terrible"),
                childAtPosition(
                    allOf(
                        withId(R.id.answer_group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialRadioButton6.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.submit_answer), withText("Submit"),
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
