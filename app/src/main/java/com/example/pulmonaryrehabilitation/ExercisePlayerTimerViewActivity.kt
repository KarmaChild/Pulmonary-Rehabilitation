package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayer
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class ExercisePlayerTimerViewActivity : AppCompatActivity() {
    lateinit var exercisePlayer: ExercisePlayer // this will be passed in
    lateinit var timer: CountDownTimer

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

        // Initialize views
        stepTitle = findViewById<TextView>(R.id.stepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.stepDescriptionLabel)
        progressBar = findViewById<ProgressBar>(R.id.timerProgressBar)

        startStep()
    }
    fun startStep() {
        updateLabelsWithData()
        updateProgressBarWithData()
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            val timerStep = step as TimerStep
            startTimer(timerStep.duration)
        }
    }
    fun endStep() {
    }
    // timer for timer steps
    // goes to next step automatically
    // updates UI
    fun startTimer(time: Int) {
        var timeLeft = time
        var timeElapsed = 0
        timer = object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timeElapsed += 1
                progressBar.progress = timeElapsed
            }
            override fun onFinish() {
                endStep()
            }
        }.start()
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
// goes to the previous step
// known bug when going from previous exercise, it has an error on tap one but then goes
//fun previousTapped() {
//    exercisePlayer.goToPreviousStep()
//    if (this::timer.isInitialized) {
//        timer.cancel()
//    }
//    startCurrentStep()
//}
//fun nextTapped() {
//    exercisePlayer.goToNextStep()
//    if (this::timer.isInitialized) {
//        timer.cancel()
//    }
//    startCurrentStep()
//}