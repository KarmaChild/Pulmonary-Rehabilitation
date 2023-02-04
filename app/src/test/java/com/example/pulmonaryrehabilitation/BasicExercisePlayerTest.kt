package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayer
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers
import org.junit.Assert.*
import org.junit.Test

class BasicExercisePlayerTest {

    @Test
    fun getExercisePlayerName() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.exercisePlayerName, "Test Collection 1")
    }

    @Test
    fun getCurrentExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.currentExercise?.exerciseName, "Test Exercise 1")
    }

    @Test
    fun getPreviousExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.previousExercise?.exerciseName, null)
    }

    @Test
    fun setCurrentExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun setPreviousExercise() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun getCurrentStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.currentStep?.stepTitle, "Test Step 1")
    }

    @Test
    fun getPreviousStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        assertEquals(testPlayer.previousStep?.stepTitle, null)
    }

    @Test
    fun setCurrentStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun setPreviousStep() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun play() {
    }

    @Test
    fun pause() {
    }

    @Test
    fun goNext() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
        testPlayer.goNext()
        // assertEquals(testPlayer.currentExercise?.exerciseName, "Test Exercise 1")
    }

    @Test
    fun goBack() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun reset() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }

    @Test
    fun getExerciseRoutine() {
        val testPlayer: ExercisePlayer = ExercisePlayers.testExercisePlayer1()
    }
}