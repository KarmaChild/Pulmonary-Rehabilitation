package com.example.pulmonaryrehabilitation.Exercises

import android.view.View
import com.example.pulmonaryrehabilitation.VideoNames

class TestStep2: ExerciseStep,ExerciseStepDuration {
    override val stepTitle: String = "Test Step 2"
    override val instruction: String = "Do thing y"
    override val stepType: StepType = StepType.TIMER
    override val visual: View
        get() = TODO("Not yet implemented")
    override val videoName: VideoNames = VideoNames.TEST
    override val durationInSeconds: Int = 13 // test
}