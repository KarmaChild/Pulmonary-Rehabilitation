package com.example.pulmonaryrehabilitation.Exercises

import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep

interface Exercise {
    val exerciseName: String
    val exerciseSteps: List<ExerciseStep>
    val completionPoints: Int
    var doStack: ArrayDeque<ExerciseStep>
    var undoStack: ArrayDeque<ExerciseStep>

    fun addStepsToDoStack()
    fun getCurrentStep(): ExerciseStep?
    fun goToNextStep(): ExerciseStep?
    fun goToPreviousStep(): ExerciseStep?
    fun resetStacks()
}