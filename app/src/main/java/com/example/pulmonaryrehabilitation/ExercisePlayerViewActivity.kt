package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExercisePlayerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_player)

        var collectionName = findViewById<TextView>(R.id.exPlayerCollectionLabel)
        var exerciseName = findViewById<TextView>(R.id.exPlayerExerciseNameLabel)
        var stepName = findViewById<TextView>(R.id.exPlayerStepNameLabel)
        var stepDescription = findViewById<TextView>(R.id.exPlayerStepDescriptionLabel)
        collectionName.text = "Hello Goodbye"
        stepDescription.text = "What's going one?"
    }
}