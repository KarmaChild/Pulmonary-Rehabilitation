package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayerObject

class ExercisePlayerTapViewActivity : AppCompatActivity() {
    lateinit var timer: CountDownTimer

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var continueButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tap_exercise_player)
        ExercisePlayerObject.addExerciseCollection() // for testing

        // Initialize views
        stepTitle = findViewById<TextView>(R.id.tapStepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.tapStepDescriptionLabel)
        continueButton = findViewById<Button>(R.id.continueButton)
        continueButton.setOnClickListener {
            endStep()
        }
        startStep()
    }
    /*
    startStep Specification
    This grabs the data from the current step and displays it via the textfields
    Pre-Condition: None
    Post-Condition: Update the UI
     */
    fun startStep() {
        updateLabelsWithData()
    }
    /*
    endStep Specification
    This decides what to do after the step is completed
    Pre-Condition: Button tapped
    Post-Condition: Goes to next step, sees if it's a timer step or a tap step
    If a tap step, it refreshes the page
    If not, it goes to the Timer Activity
     */
    fun endStep() {
        ExercisePlayerObject.exercise.goToNextStep()
        val test = ExercisePlayerObject.exercise
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            val intent = Intent(this@ExercisePlayerTapViewActivity, ExercisePlayerTimerViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // gets rid of the animation
        } else {
            startStep()
        }
    }

    fun updateLabelsWithData() {
        val step = getCurrentStep()
        stepTitle.text = step?.stepTitle
        stepDescription.text = step?.instruction
    }

    fun getCurrentStep(): ExerciseStep? {
        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
}