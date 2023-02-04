package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollection
import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep

interface ExercisePlayer {
    val exercisePlayerName: String
    val exerciseRoutine: ExerciseCollection
    var currentExercise: Exercise?
    var previousExercise: Exercise?
    var currentStep: ExerciseStep?
    var previousStep: ExerciseStep?

    fun play()
    fun pause()
    fun goNext()
    fun goBack()
    fun reset()
}