package com.example.pulmonaryrehabilitation.Exercises

import android.view.View
import com.example.pulmonaryrehabilitation.VideoNames

interface ExerciseStep {
    val stepTitle: String
    val instruction: String
    val stepType: StepType
    val visual: View // View type might be the wrong type but this will be
    // a view with the icon or timer or whatever
    val videoName: VideoNames // we'll have a video loading class that loads the clip
    // based of the name
    // test
    fun action() {}
}