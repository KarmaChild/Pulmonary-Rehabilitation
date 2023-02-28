package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollection
import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep

interface ExercisePlayer {
    val exercisePlayerName: String
    val exerciseRoutine: ExerciseCollection

    fun play(): MutableList<String>?
    fun playTimer(duration: Int)
    fun pause()
    fun goToNextExercise(): Exercise?
    fun goToPreviousExercise(): Exercise?
    fun goToNextStep(): ExerciseStep?
    fun goToPreviousStep(): ExerciseStep?
    fun resetExercises()
    fun resetSteps()
}