package com.example.pulmonaryrehabilitation.Exercises

interface Exercise {
    val exerciseName: String
    val exerciseSteps: List<ExerciseStep>
    val completionPoints: Int // for gamification
    // We create 2 stacks of steps which allows a user to go
    // back to the previous if they want
    var doStack: ArrayDeque<ExerciseStep>
    var undoStack: ArrayDeque<ExerciseStep>
    fun addAllStepsToDoStack()

}