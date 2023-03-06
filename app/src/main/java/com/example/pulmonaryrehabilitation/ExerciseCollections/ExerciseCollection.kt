package com.example.pulmonaryrehabilitation.ExerciseCollections

import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.activity.media.ImageNames

interface ExerciseCollection {
    val collectionName: String
    var exercises: List<Exercise>
    var icon: ImageNames
    var doStack: ArrayDeque<Exercise>
    var undoStack: ArrayDeque<Exercise>

    fun getCurrentExercise(): Exercise?
    fun goToNextExercise(): Exercise?
    fun goToPreviousExercise(): Exercise?
    fun resetStacks()
}