package com.example.pulmonaryrehabilitation.model

import com.google.firebase.database.Exclude

class ExerciseDataClass(
    override var id: String = "",
    override var video: String = "",
    override var title: String = "",
    override var medicalType: String = "",
    override var description: String = ""
) : ExerciseData {

    override fun toString(): String {
        return "ExerciseDataClass(id='$id', video='$video', title='$title', medicalType='$medicalType', description='$description')"
    }

    @Exclude
    override fun toExerciseDataMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "video" to video,
            "title" to title,
            "medicalType" to medicalType,
            "description" to description
        )
    }
}