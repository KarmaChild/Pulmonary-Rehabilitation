package com.example.pulmonaryrehabilitation.Exercises.Steps

import com.example.pulmonaryrehabilitation.activity.ImageNames
import com.example.pulmonaryrehabilitation.activity.VideoNames

data class TapStep(
    override val stepTitle: String,
    override val instruction: String,
    val icons: List<ImageNames>,
    val video: VideoNames // not part of exercise step because there may
    // be no video for this type of step
) : ExerciseStep {
    override val action: StepActions = StepActions.NEXT_STEP
}