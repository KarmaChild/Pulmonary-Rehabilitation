package com.example.pulmonaryrehabilitation.member

interface ExerciseData {
    var id: String
    var video: String
    var title: String
    var medicalType: String
    var description: String

    override fun toString(): String
    fun toExerciseDataMap(): Map<String, Any>
}