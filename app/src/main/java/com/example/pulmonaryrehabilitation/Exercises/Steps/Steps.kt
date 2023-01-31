package com.example.pulmonaryrehabilitation.Exercises.Steps

import com.example.pulmonaryrehabilitation.ImageNames
import com.example.pulmonaryrehabilitation.VideoNames

object Steps {
    fun testStep1(): ExerciseStep {
        return TapStep(
            "Test Step 1", "Do thing x",
            listOf(ImageNames.TEST), VideoNames.TEST
        )
    }
    fun testStep2(duration: Int): ExerciseStep {
        return TimerStep(
            "Test Step 2", "Do thing y",
            duration, VideoNames.TEST
        )
    }
}