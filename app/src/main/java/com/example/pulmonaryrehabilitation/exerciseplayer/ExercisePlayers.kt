package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollections

object ExercisePlayers {
    fun testExercisePlayer1(): ExercisePlayer {
        return BasicExercisePlayer(ExerciseCollections.testCollection1())
    }
}