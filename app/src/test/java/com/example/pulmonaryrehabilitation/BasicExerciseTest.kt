package com.example.pulmonaryrehabilitation
import com.example.pulmonaryrehabilitation.Exercises.Exercises
import org.junit.Assert.*
import org.junit.Test
class BasicExerciseTest {
    @Test
    fun testInitialization() {
        val testExercise = Exercises.testExercise1()
        assertEquals(testExercise.exerciseName, "Test Exercise 1")
        assertEquals(testExercise.doStack.size, 2)
    }
    @Test
    fun testGetCurrentStep() {
        val testExercise = Exercises.testExercise1()
        assertEquals(testExercise.getCurrentStep()?.stepTitle, "Test Step 1")
    }
    @Test
    fun testSwitchingSteps() {
        val testExercise = Exercises.testExercise1()

        testExercise.goToNextStep()
        assertEquals(testExercise.getCurrentStep()?.stepTitle, "Test Step 2")
        assertEquals(testExercise.goToNextStep(), null)
        assertEquals(testExercise.goToPreviousStep()?.stepTitle, "Test Step 2")
        assertEquals(testExercise.goToPreviousStep()?.stepTitle, "Test Step 1")
        assertEquals(testExercise.goToPreviousStep(), null)
        testExercise.resetStacks()
        assertEquals(testExercise.doStack.size, 2)
        assertEquals(testExercise.undoStack.size, 0)
    }
}