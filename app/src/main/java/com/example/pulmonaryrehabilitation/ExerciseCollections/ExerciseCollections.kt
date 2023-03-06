package com.example.pulmonaryrehabilitation.ExerciseCollections

import com.example.pulmonaryrehabilitation.Exercises.Exercises
import com.example.pulmonaryrehabilitation.activity.media.ImageNames

object ExerciseCollections {
    fun testCollection1(): ExerciseCollection {
        return BasicCollection(
            "Test Collection 1",
            listOf(Exercises.testExercise1(), Exercises.testExercise2()),
            ImageNames.TEST
        )
    }
}