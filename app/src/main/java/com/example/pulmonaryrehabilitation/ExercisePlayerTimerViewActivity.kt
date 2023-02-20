package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TapStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayerObject

class ExercisePlayerTimerViewActivity : AppCompatActivity() {
//    lateinit var exercisePlayer: ExercisePlayer // this will be passed in
    lateinit var timer: CountDownTimer

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var progressBar: ProgressBar
    lateinit var videoView: VideoView

    /*
    val videoView: VideoView = findViewById(R.id.spikeID)
        val pathString = "android.resource://" + packageName + "/" + R.raw.spikepvideo
        val uri: Uri = Uri.parse(pathString)
        videoView.setVideoURI(uri)

        val mediaController: MediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_exercise_player_view)


        // Initialize views
        stepTitle = findViewById<TextView>(R.id.stepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.stepDescriptionLabel)
        progressBar = findViewById<ProgressBar>(R.id.timerProgressBar)
        videoView = findViewById<VideoView>(R.id.timerVideoView)

        startStep()
    }
    /*
    startStep Specification
    This grabs the data from the current step and displays it via the textfields and progress bar
    It then double checks that this is a timer step and then starts the timer
    Pre-Condition: The current step is a Timer Step
    Post-Condition: Update the UI and Start the timer
     */
    fun startStep() {
        updateLabelsWithData()
        updateProgressBarWithData()
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            val timerStep = step as TimerStep
            startTimer(timerStep.duration)
        }
    }
    /*
    endStep Specification
    This decides what to do after the step is completed
    Pre-Condition: Timer ended
    Post-Condition: Goes to next step, sees if it's a timer step or a tap step
    If a tap set it goes to the Tap Activity
    If not, it refreshes the current view with the new data
     */
    fun endStep() {
        ExercisePlayerObject.exercise.goToNextStep()


        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TapStep::class) {
            val intent = Intent(this@ExercisePlayerTimerViewActivity, ExercisePlayerTapViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // gets rid of the animation
        } else {
            startStep()
        }
    }
    /*

     */
    fun startTimer(time: Int) {
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
        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
}
// goes to the previous step
// known bug when going from previous exercise, it has an error on tap one but then goes
// fun previousTapped() {
//    exercisePlayer.goToPreviousStep()
//    if (this::timer.isInitialized) {
//        timer.cancel()
//    }
//    startCurrentStep()
// }
// fun nextTapped() {
//    exercisePlayer.goToNextStep()
//    if (this::timer.isInitialized) {
//        timer.cancel()
//    }
//    startCurrentStep()
// }