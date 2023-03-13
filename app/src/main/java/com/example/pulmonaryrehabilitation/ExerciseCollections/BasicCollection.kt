package com.example.pulmonaryrehabilitation.ExerciseCollections

import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.activity.media.ImageNames

class BasicCollection(
    override val collectionName: String,
    override var exercises: List<Exercise>,
    override var icon: ImageNames
) : ExerciseCollection {
    override var doStack: ArrayDeque<Exercise> = ArrayDeque()
    override var undoStack: ArrayDeque<Exercise> = ArrayDeque()
    init {
        addExercisesToDoStack()
    }
    fun addExercisesToDoStack() {
        for (step in exercises.reversed()) { // reverse because LIFO
            doStack.add(step)
        }
    }
    override fun getCurrentExercise(): Exercise? {
        if (!doStack.isEmpty()) {
            return doStack.last()
        } else {
            return null
        }
    }
    override fun goToNextExercise(): Exercise? {
        if (!doStack.isEmpty()) {
            val currentStep = doStack.removeLast()
            undoStack.add(currentStep)
            return getCurrentExercise()
        } else {
            return null // returns null so exercise player knows to go to next exercise
        }
    }
    override fun goToPreviousExercise(): Exercise? {
        if (!undoStack.isEmpty()) {
            val previousStep = undoStack.removeLast()
            doStack.add(previousStep)
            return getCurrentExercise()
        } else {
            return null // returns null so exercise player knows to go to previous exercise
        }
    }
    override fun resetStacks() {
        doStack = ArrayDeque()
        undoStack = ArrayDeque()
        addExercisesToDoStack()
    }
}