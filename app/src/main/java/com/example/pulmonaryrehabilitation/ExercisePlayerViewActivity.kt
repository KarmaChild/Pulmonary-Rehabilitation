package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class ExercisePlayerViewActivity : AppCompatActivity() {
    var exercisePlayer = ExercisePlayers.testExercisePlayer1()
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_player)

        getInfoFromPlayer()
    }
    fun getInfoFromPlayer() {
        val collectionName: TextView = findViewById<TextView>(R.id.exPlayerCollectionLabel)
        val exerciseName = findViewById<TextView>(R.id.exPlayerExerciseNameLabel)
        val stepName = findViewById<TextView>(R.id.exPlayerStepNameLabel)
        val stepDescription = findViewById<TextView>(R.id.exPlayerStepDescriptionLabel)

        collectionName.text = exercisePlayer.exerciseRoutine.collectionName
        exerciseName.text = exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        stepName.text = exercise?.exerciseName

        stepDescription.text = exercise?.getCurrentStep()?.instruction
    }
}