package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class ExercisePlayerViewActivity : AppCompatActivity() {
    var exercisePlayer = ExercisePlayers.testExercisePlayer1()

    var collectionName = findViewById<TextView>(R.id.exPlayerCollectionLabel)
    var exerciseName = findViewById<TextView>(R.id.exPlayerExerciseNameLabel)
    var stepName = findViewById<TextView>(R.id.exPlayerStepNameLabel)
    var stepDescription = findViewById<TextView>(R.id.exPlayerStepDescriptionLabel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_player)

        getInfoFromPlayer()
    }
    fun getInfoFromPlayer() {
        collectionName.text = exercisePlayer.exerciseRoutine.collectionName
        exerciseName.text = exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        stepName.text = exercise?.exerciseName
//        stepDescription.text =
//            exercisePlayer.exerciseRoutine
//                .getCurrentExercise()?.getCurrentStep()?.instruction
    }
}