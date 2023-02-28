package com.example.pulmonaryrehabilitation.Exercises

import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep

class BasicExercise(
    override val exerciseName: String,
    override val exerciseSteps: List<ExerciseStep>,
    override val completionPoints: Int
) : Exercise {
    // internet said to use ArrayDeque for stacks
    override var doStack: ArrayDeque<ExerciseStep> = ArrayDeque()
    override var undoStack: ArrayDeque<ExerciseStep> = ArrayDeque()
    init {
        addStepsToDoStack()
    }
    override fun addStepsToDoStack() {
        for (step in exerciseSteps.reversed()) { // reverse because LIFO
            doStack.add(step)
        }
    }
    override fun getCurrentStep(): ExerciseStep? {
        if (!doStack.isEmpty()) {
            return doStack.last()
        } else {
            return null
        }
    }
    override fun goToNextStep(): ExerciseStep? {
        if (!doStack.isEmpty()) {
            val currentStep = doStack.removeLast()
            undoStack.add(currentStep)
            return getCurrentStep()
        } else {
            return null // returns null so exercise player knows to go to next exercise
        }
    }
    override fun goToPreviousStep(): ExerciseStep? {
        if (!undoStack.isEmpty()) {
            val previousStep = undoStack.removeLast()
            doStack.add(previousStep)
            return getCurrentStep()
        } else {
            return null // returns null so exercise player knows to go to previous exercise
        }
    }
    override fun resetStacks() {
        doStack = ArrayDeque()
        undoStack = ArrayDeque()
        addStepsToDoStack()
    }
}
