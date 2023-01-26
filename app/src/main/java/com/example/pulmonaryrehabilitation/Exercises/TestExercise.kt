package com.example.pulmonaryrehabilitation.Exercises

class TestExercise : Exercise {
    override val exerciseName: String = "Test Exercise"
    override val exerciseSteps: List<ExerciseStep> = listOf(TestStep1(), TestStep2())
    override val completionPoints: Int = 13
    override var doStack: ArrayDeque<ExerciseStep>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var undoStack: ArrayDeque<ExerciseStep>
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun addAllStepsToDoStack() {
        TODO("Not yet implemented")
    }

}