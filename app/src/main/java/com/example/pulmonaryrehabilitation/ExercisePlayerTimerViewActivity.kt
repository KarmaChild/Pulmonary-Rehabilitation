package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayer
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class ExercisePlayerTimerViewActivity : AppCompatActivity() {
    lateinit var exercisePlayer: ExercisePlayer // this will be passed in

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_exercise_player_view)
        // Testing
        exercisePlayer = ExercisePlayers.testExercisePlayer1()
        exercisePlayer.goToNextStep()
        // END Testing

        stepTitle = findViewById<TextView>(R.id.stepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.stepDescriptionLabel)
        progressBar = findViewById<ProgressBar>(R.id.timerProgressBar)
        updateLabelsWithData()
        updateProgressBarWithData()
    }
    fun updateLabelsWithData() {
        val step = getCurrentStep()
        stepTitle.text = step?.stepTitle
        stepDescription.text = step?.instruction
    }
    fun updateProgressBarWithData() {
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            val timerStep = step as TimerStep
            progressBar.max = timerStep.duration
            progressBar.progress = 0
        }
    }
    fun getCurrentStep(): ExerciseStep? {
        return exercisePlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
}