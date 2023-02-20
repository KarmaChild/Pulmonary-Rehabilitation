package com.example.pulmonaryrehabilitation.exerciseplayer

// Why does this exist? Because Android handles passing objects in a weird way (Serialization/
// Parcable) and out exercise player object is too complicated for this
object ExercisePlayerObject {
    lateinit var exercise: ExercisePlayer
    fun addExerciseCollection() {
        if (::exercise.isInitialized == false) { // this is for testing
            exercise = ExercisePlayers.testExercisePlayer1()
        }
    }
}