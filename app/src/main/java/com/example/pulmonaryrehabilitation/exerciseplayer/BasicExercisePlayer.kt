package com.example.pulmonaryrehabilitation.exerciseplayer

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollection
import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import org.junit.Assert.assertNotNull

class BasicExercisePlayer(
    override val exerciseRoutine: ExerciseCollection,
) : ExercisePlayer {
    override val exercisePlayerName: String = exerciseRoutine.collectionName
    init {
        assertNotNull(exerciseRoutine)
    }

    override fun play(): MutableList<String> {
        val tempList = mutableListOf<String>()
        val exercises = exerciseRoutine.exercises
        for (exercise in exercises.iterator()) {
            // println(exercise.exerciseName)
            tempList.add(exercise.exerciseName)
            for (step in exercise.exerciseSteps.iterator()) {
                // println(step.stepTitle)
                tempList.add(step.stepTitle)
            }
        }
        return tempList
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun goToNextExercise(): Exercise? {
        exerciseRoutine.goToNextExercise()
        return exerciseRoutine.getCurrentExercise()
    }

    override fun goToPreviousExercise(): Exercise? {
        exerciseRoutine.goToPreviousExercise()
        return exerciseRoutine.getCurrentExercise()
    }

    override fun goToNextStep(): ExerciseStep? {
        exerciseRoutine.getCurrentExercise()?.goToNextStep()
        return exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }

    override fun goToPreviousStep(): ExerciseStep? {
        exerciseRoutine.getCurrentExercise()?.goToPreviousStep()
        return exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }

    override fun resetExercises() {
        exerciseRoutine.resetStacks()
    }

    override fun resetSteps() {
        exerciseRoutine.getCurrentExercise()?.resetStacks()
    }
}