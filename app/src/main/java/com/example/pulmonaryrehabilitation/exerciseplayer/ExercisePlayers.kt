package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollections

object ExercisePlayers {
    fun testExercisePlayer1(): ExercisePlayer {
        return BasicExercisePlayer(ExerciseCollections.testCollection1())
    }
}

fun main(args: Array<String>) {
    println(ExercisePlayers.testExercisePlayer1().play())
    ExercisePlayers.testExercisePlayer1().playTimer(2000)
}