package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.member.StepHistoryClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class StepHistoryClassTest {

    @Test
    fun stepHistoryToStringTest() {
        val test = StepHistoryClass("stepCount")
        Assert.assertEquals(
            "{stepCount='stepCount'}",
            test.toString()
        )
    }
}