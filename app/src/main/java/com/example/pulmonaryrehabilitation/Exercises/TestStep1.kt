package com.example.pulmonaryrehabilitation.Exercises

import android.view.View
import com.example.pulmonaryrehabilitation.VideoNames

class TestStep1: ExerciseStep {
    override val stepTitle: String = "Test Step 1"
    override val instruction: String = "Do thing x"
    override val stepType: StepType = StepType.GATHER
    override val visual: View
        get() = TODO("Not yet implemented")
    override val videoName: VideoNames = VideoNames.TEST
}