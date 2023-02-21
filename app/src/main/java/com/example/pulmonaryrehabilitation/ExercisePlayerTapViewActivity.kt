package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayerObject

class ExercisePlayerTapViewActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private lateinit var detector: GestureDetectorCompat

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var continueButton: Button
    lateinit var exerciseName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tap_exercise_player)
        ExercisePlayerObject.addExerciseCollection() // for testing
        detector = GestureDetectorCompat(this, SwipeListener())
        // Initialize views
        stepTitle = findViewById<TextView>(R.id.tapStepTitleLabel)
        stepDescription = findViewById<TextView>(R.id.tapStepDescriptionLabel)
        continueButton = findViewById<Button>(R.id.continueButton)
        exerciseName = findViewById<TextView>(R.id.tapExerciseName)
        continueButton.setOnClickListener {
            endStep()
        }
        startStep()
    }

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
        changeStep()
    }
    fun changeStep() {
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
        exerciseName.text = ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.exerciseName
    }

    fun getCurrentStep(): ExerciseStep? {
        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
    fun swipeRight() {
        ExercisePlayerObject.exercise.goToPreviousStep()
        val text = getCurrentStep()
        changeStep()

//    startCurrentStep()
    }
    fun swipeLeft() {
        ExercisePlayerObject.exercise.goToNextStep()
        val test = getCurrentStep()
        changeStep()
    }

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
                if (Math.abs(diffX) > 100) {
                    if (diffX > 0) {
                        // right swipe
                        this@ExercisePlayerTapViewActivity.swipeRight()
                    } else {
                        // left swipe
                        this@ExercisePlayerTapViewActivity.swipeLeft()
                    }
                } else {
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
            return super.onFling(downEvent, moveEvent, velocityX, velocityY)
        }
    }
}