package com.example.pulmonaryrehabilitation
import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollections
import org.junit.Assert.*
import org.junit.Test
class BasicCollectionTest {
    @Test
    fun testInitialization() {
        val testCollection = ExerciseCollections.testCollection1()
        assertEquals(testCollection.collectionName, "Test Collection 1")
        assertEquals(testCollection.doStack.size, 2)
    }
    @Test
    fun testGetCurrentExercise() {
        val testCollection = ExerciseCollections.testCollection1()
        assertEquals(testCollection.getCurrentExercise()?.exerciseName, "Test Exercise 1")
    }
    @Test
    fun testSwitchingSteps() {
        val testCollection = ExerciseCollections.testCollection1()

        testCollection.goToNextExercise()
        assertEquals(testCollection.getCurrentExercise()?.exerciseName, "Test Exercise 2")
        assertEquals(testCollection.goToNextExercise(), null)
        assertEquals(testCollection.goToPreviousExercise()?.exerciseName, "Test Exercise 2")
        assertEquals(testCollection.goToPreviousExercise()?.exerciseName, "Test Exercise 1")
        assertEquals(testCollection.goToPreviousExercise(), null)
        testCollection.resetStacks()
        assertEquals(testCollection.doStack.size, 2)
        assertEquals(testCollection.undoStack.size, 0)
    }
}