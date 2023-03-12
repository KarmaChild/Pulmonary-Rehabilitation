package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.member.ExerciseDataClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class ExerciseDataClassTest {

    @Test
    fun exerciseDataToStringTest() {
        val test = ExerciseDataClass("id", "video", "title", "medicalType", "description")
        Assert.assertEquals(
            "ExerciseDataClass(id='id', video='video', title='title', medicalType='medicalType', description='description')",
            test.toString()
        )
    }

    @Test
    fun toExerciseDataMapTest() {
        val test = ExerciseDataClass("id", "video", "title", "medicalType", "description")
        val testMap = mutableMapOf<String, Any>("id" to "id", "video" to "video", "title" to "title", "medicalType" to "medicalType", "description" to "description")
        Assert.assertEquals(testMap, test.toExerciseDataMap())
    }
}