package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers
import org.junit.Assert
import org.junit.Test

class ExercisePlayerViewActivityTest {
    @Test
    fun TestCase() {
        var exercisePlayer = ExercisePlayers.testExercisePlayer1()

        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()

        Assert.assertEquals(exercisePlayer.exerciseRoutine.collectionName, "Test Collection 1")
        Assert.assertEquals(exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName, "Test Exercise 1")
        Assert.assertEquals(exercise?.getCurrentStep()?.stepTitle, "Test Step 1")
        Assert.assertEquals(exercise?.getCurrentStep()?.instruction, "Do thing x")

        exercisePlayer.goToNextStep()

        Assert.assertEquals(exercisePlayer.exerciseRoutine.collectionName, "Test Collection 1")
        Assert.assertEquals(exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName, "Test Exercise 1")
        Assert.assertEquals(exercise?.getCurrentStep()?.stepTitle, "Test Step 2")
        Assert.assertEquals(exercise?.getCurrentStep()?.instruction, "Do thing y")
    }
}