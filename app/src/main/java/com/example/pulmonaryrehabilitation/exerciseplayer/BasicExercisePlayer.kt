package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollection
import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep

class BasicExercisePlayer(
    override val exerciseRoutine: ExerciseCollection,
) : ExercisePlayer {
    override val exercisePlayerName: String = exerciseRoutine.collectionName
    override var currentExercise: Exercise? = exerciseRoutine.getCurrentExercise()
    override var previousExercise: Exercise? = exerciseRoutine.goToPreviousExercise()
    override var currentStep: ExerciseStep? = exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    override var previousStep: ExerciseStep? = exerciseRoutine.goToPreviousExercise()
        ?.goToPreviousStep()

    override fun play() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun goNext() {

    }

    override fun goBack() {

    }

    override fun reset() {

    }
}