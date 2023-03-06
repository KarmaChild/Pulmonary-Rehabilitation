package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.exerciseplayerclass.ExercisePlayer
import com.example.pulmonaryrehabilitation.exerciseplayerclass.ExercisePlayers
import org.junit.Assert.*
import org.junit.Test

class BasicExercisePlayerTest {

    @Test
    fun getExerciseRoutine() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.exerciseRoutine.collectionName, "Test Collection 1")
    }

    @Test
    fun getExercisePlayerName() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.exercisePlayerName, "Test Collection 1")
    }
    @Test
    fun play() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        val testArray = arrayListOf(
            "Test Exercise 1", "Test Step 1", "Test Step 2",
            "Test Exercise 2", "Test Step 1", "Test Step 2"
        )
        assertEquals(testPlayer.play(), testArray)
    }

    @Test
    fun pause() {
    }

    @Test
    fun goToNextExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.goToNextExercise()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.exerciseName,
            "Test Exercise 2"
        )
    }

    @Test
    fun goToPreviousExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.goToNextExercise()
        testPlayer.goToPreviousExercise()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.exerciseName,
            "Test Exercise 1"
        )
    }

    @Test
    fun goToNextStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.exerciseRoutine.getCurrentExercise()?.goToNextStep()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()?.stepTitle,
            "Test Step 2"
        )
    }

    @Test
    fun goToPreviousStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.exerciseRoutine.getCurrentExercise()?.goToNextStep()
        testPlayer.exerciseRoutine.getCurrentExercise()?.goToPreviousStep()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()?.stepTitle,
            "Test Step 1"
        )
    }

    @Test
    fun resetExercises() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.goToNextExercise()
        testPlayer.resetExercises()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.exerciseName,
            "Test Exercise 1"
        )
    }
    @Test
    fun resetSteps() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.exerciseRoutine.getCurrentExercise()?.goToNextStep()
        testPlayer.exerciseRoutine.getCurrentExercise()?.resetStacks()
        assertEquals(
            testPlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()?.stepTitle,
            "Test Step 1"
        )
    }

    @Test
    fun test() {
        val duration = 3
        assertTrue("duration must be a positive integer greater than zero", duration > 0)
        assertFalse("duration must be a positive integer greater than zero", duration == 0)
    }
}
