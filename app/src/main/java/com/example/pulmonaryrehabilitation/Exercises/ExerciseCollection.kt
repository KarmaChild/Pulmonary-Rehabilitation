package com.example.pulmonaryrehabilitation.Exercises

import com.example.pulmonaryrehabilitation.ImageNames

interface ExerciseCollection {
    val collectionName: String
    var exercises: List<Exercise>
    var icon: ImageNames

    fun getDuration()
    fun getTotalPoints()
}