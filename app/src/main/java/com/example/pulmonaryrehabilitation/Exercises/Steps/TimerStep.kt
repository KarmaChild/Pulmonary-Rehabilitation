package com.example.pulmonaryrehabilitation.Exercises.Steps

import com.example.pulmonaryrehabilitation.activity.media.VideoNames

data class TimerStep(
    override val stepTitle: String,
    override val instruction: String,
    val duration: Int,
    val video: VideoNames
) : ExerciseStep {
    override val action: StepActions = StepActions.PLAY_PAUSE
}