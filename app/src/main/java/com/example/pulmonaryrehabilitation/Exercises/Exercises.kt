package com.example.pulmonaryrehabilitation.Exercises

import com.example.pulmonaryrehabilitation.Exercises.Steps.Steps

object Exercises {
    fun testExercise1(): Exercise {
        return BasicExercise(
            "Test Exercise 1",
            listOf(Steps.testStep1(), Steps.testStep2(10)), 100000
        )
    }
    fun testExercise2(): Exercise {
        return BasicExercise(
            "Test Exercise 2",
            listOf(Steps.testStep1(), Steps.testStep2(10)), 100000
        )
    }
}
