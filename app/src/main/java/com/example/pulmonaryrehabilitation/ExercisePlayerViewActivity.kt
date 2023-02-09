package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class ExercisePlayerViewActivity : AppCompatActivity() {
    var exercisePlayer = ExercisePlayers.testExercisePlayer1()
    // Take in a collection
    // Get the exercise from the collection
    // Check what type of step it is and if it's a timer set it here
    // collection.getNextStep
    // get the step, update the UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_player)

        startStep()
    }

    fun startStep() { // starts the timer for each step
        // this is a mock implementation for now
        displayCurrentData()
        startTimer(10)
    }
    fun displayCurrentData() {
        // this get's the text views and populates them with the data for
        // the current collection, exercise, step
        val collectionName: TextView = findViewById<TextView>(R.id.exPlayerCollectionLabel)
        val exerciseName = findViewById<TextView>(R.id.exPlayerExerciseNameLabel)
        val stepName = findViewById<TextView>(R.id.exPlayerStepNameLabel)
        val stepDescription = findViewById<TextView>(R.id.exPlayerStepDescriptionLabel)
        collectionName.text = getCurrentCollectionName()
        exerciseName.text = getCurrentExerciseName()
        stepName.text = getCurrentStepName()
        stepDescription.text = getCurrentStepDescription()
    }
    fun startTimer(time: Int) {
        var timeLeft = time
        object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft -= 1
            }
            override fun onFinish() {
                exercisePlayer.goToNextStep()
                startStep()
            }
        }.start()
    }

    fun getCurrentCollectionName(): String {
        return exercisePlayer.exerciseRoutine.collectionName
    }
    fun getCurrentExerciseName(): String {
        return exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName ?: "Error"
        // The ?: "" returns a default if it's null
    }
    fun getCurrentStepName(): String {
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        return exercise?.getCurrentStep()?.stepTitle ?: "Error"
    }
    fun getCurrentStepDescription(): String {
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        return exercise?.getCurrentStep()?.instruction ?: "Error"
    }
}