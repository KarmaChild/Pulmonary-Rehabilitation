package com.example.pulmonaryrehabilitation.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TapStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayerObject
import com.example.pulmonaryrehabilitation.model.CurrentUser

class ExercisePlayerTimerViewActivity : AppCompatActivity() {
    lateinit var timer: CountDownTimer
    private lateinit var detector: GestureDetectorCompat

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var progressBar: ProgressBar
    lateinit var videoView: VideoView
    lateinit var exerciseName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Exercise Player", "Timer Step Started")

        setContentView(R.layout.activity_timer_exercise_player_view)
        detector = GestureDetectorCompat(this, SwipeListener()) // detects user swipes
        // detector functionality comes from the innerclass SwipeListener()

        // Initialize views
        stepTitle = findViewById<TextView>(R.id.stepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.stepDescriptionLabel)
        progressBar = findViewById<ProgressBar>(R.id.timerProgressBar)
        videoView = findViewById<VideoView>(R.id.timerVideoView)
        exerciseName = findViewById<TextView>(R.id.timerViewExerciseName)

        startStep()
    }
    /*
    onTouchEvent Specification
    This overrides the default onTouchEvent to add swipe detection
    If the interaction wasn't a swipe it defaults to the built in function
    Pre-Condition: A user touch event
    Post-Condition: Boolean that signifies the input was successfully recieved
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (detector.onTouchEvent(event!!)) {
                return true
            } else {
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
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
            // step.video is the enum value of the video and will return R.id.videoName
            val pathString = "android.resource://" + packageName + "/" + step.video.resource

            val uri: Uri = Uri.parse(pathString)
            videoView.setVideoURI(uri)
            videoView.start()
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
        changeStep()
    }
    /*
    changeStep Specification
    Looks at the current step and decides if it needs to change activities or refresh the current one
    Pre-Condition: None
    Post-Condition: Show the next step in the current activity or the appropriate one
     */
    fun changeStep() {
        // stop the video and timer in case they're still running
        videoView.stopPlayback()
        timer.cancel()
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TapStep::class) {
            Log.i("Change Step", "New step is tap step (From timer step)")

            val intent = Intent(this@ExercisePlayerTimerViewActivity, ExercisePlayerTapViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // gets rid of the animation
        } else if (step == null && CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3)) {
            Log.i("Change Step", "No new step, end routine (From timer step)")

            CurrentUser.addUsageHistory(ExercisePlayerObject.exercise.exerciseRoutine.collectionName)
            // Saves the collection name in Firebase when finished
            val intent = Intent(this@ExercisePlayerTimerViewActivity, QuestionnaireActivity::class.java)
            startActivity(intent)
        } else if (step == null && !(CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3))) {
            Log.i("Change Step", "No new step, end routine (From timer step)")

            CurrentUser.addUsageHistory(ExercisePlayerObject.exercise.exerciseRoutine.collectionName)
            // Saves the collection name in Firebase when finished
            val intent = Intent(this@ExercisePlayerTimerViewActivity, DashboardActivity::class.java)
            startActivity(intent)
        } else {
            Log.i("Change Step", "New step is timer step (Currently timer step)")

            startStep()
        }
    }
    /*

     */
    fun startTimer(time: Int) {
        var timeElapsed = 0
        timer = object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // update the progress bar
                timeElapsed += 1
                progressBar.progress = timeElapsed
            }
            override fun onFinish() {
                endStep()
            }
        }.start()
    }
    /*
    updateLabelsWithData Specification
    Pre-Condition: None
    Post-Condition: Updates the labels in the view with the current steps data
     */
    private fun updateLabelsWithData() {
        val step = getCurrentStep()
        stepTitle.text = step?.stepTitle
        stepDescription.text = step?.instruction
        exerciseName.text = ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.exerciseName
    }
    /*
    updateProgressBarWithData Specification
    Pre-Condition: None
    Post-Condition: Changes the max of the progress bar to the duration of the step
     */
    private fun updateProgressBarWithData() {
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            val timerStep = step as TimerStep
            progressBar.max = timerStep.duration
            progressBar.progress = 0
        }
    }
    /*
    getCurrentStep Specification
    Provides a shortcut to the current step so no need to navigate through the objects
    Pre-Condition: None
    Post-Condition: Returns null (if no current step) or the current step

     */
    private fun getCurrentStep(): ExerciseStep? {
        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
    /*
    swipeRight/swipeLeft Specifications
    Goes to the previous or next step respectively
    Pre-Condition: None
    Post-Condition: Changes the current step and updates the UI
     */
    fun swipeRight() {
        Log.i("User Interaction", "Swipe to previous step (TimerExercisePlayer)")
        ExercisePlayerObject.exercise.goToPreviousStep()
        changeStep()

//    startCurrentStep()
    }
    fun swipeLeft() {
        Log.i("User Interaction", "Swipe to next step (TimerExercisePlayer)")
        ExercisePlayerObject.exercise.goToNextStep()
        changeStep()
    }
    /*
    SwipeListener Specification
    This is an inner class who's sole purpose is to add right and left swipes to the activity
     */
    inner class SwipeListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            downEvent: MotionEvent,
            moveEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            if (Math.abs(diffX) > Math.abs(diffY)) {
                // left or right swipe
                if (Math.abs(diffX) > 100) { // 100 is the threshold for movement
                    if (diffX > 0) {
                        // right swipe
                        this@ExercisePlayerTimerViewActivity.swipeRight()
                    } else {
                        // left swipe
                        this@ExercisePlayerTimerViewActivity.swipeLeft()
                    }
                    Log.i("Swipe Gesture", "Successful Swipe Gesture")
                } else {
                    Log.i("Swipe Gesture", "Swipe was not long enough (${Math.abs(diffX)})")
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
            return super.onFling(downEvent, moveEvent, velocityX, velocityY)
        }
    }
}
