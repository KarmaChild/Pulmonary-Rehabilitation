package com.example.pulmonaryrehabilitation.Exercises.Steps

interface ExerciseStep {
    val stepTitle: String
    val instruction: String
    val action: StepActions
}