package com.example.pulmonaryrehabilitation.activity.exerciseplayeractivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.QuestionnaireActivity
import com.example.pulmonaryrehabilitation.exerciseplayerclass.ExercisePlayerObject
import com.example.pulmonaryrehabilitation.member.CurrentUser

class ExercisePlayerTapViewActivity : AppCompatActivity() {
    private lateinit var detector: GestureDetectorCompat

    lateinit var stepTitle: TextView
    lateinit var stepDescription: TextView
    lateinit var continueButton: Button
    lateinit var exerciseName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Exercise Player", "Tap Step Started")
        setContentView(R.layout.activity_tap_exercise_player)
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
    /*
    changeStep Specification
    Looks at the current step and decides if it needs to change activities or refresh the current one
    Pre-Condition: None
    Post-Condition: Show the next step in the current activity or the appropriate one
     */
    fun changeStep() {
        val step = getCurrentStep()
        if (step?.javaClass?.kotlin == TimerStep::class) {
            Log.i("Change Step", "New step is timer step (From tap step)")
            val intent = Intent(this@ExercisePlayerTapViewActivity, ExercisePlayerTimerViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // gets rid of the animation
        } else if (step == null && CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3)) {
            Log.d("marc", "")
            Log.i("Change Step", "No new step, end routine (From tap step)")
            CurrentUser.addUsageHistory(ExercisePlayerObject.exercise.exerciseRoutine.collectionName)
            // Saves the collection name in Firebase when finished
            val intent = Intent(this@ExercisePlayerTapViewActivity, QuestionnaireActivity::class.java)
            startActivity(intent)
        } else if (step == null && !(CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3))) {
            Log.d("pol", "")
            Log.i("Change Step", "No new step, end routine (From tap step)")
            CurrentUser.addUsageHistory(ExercisePlayerObject.exercise.exerciseRoutine.collectionName)
            // Saves the collection name in Firebase when finished
            val intent =
                Intent(this@ExercisePlayerTapViewActivity, DashboardActivity::class.java)
            startActivity(intent)
        } else {
            Log.i("Change Step", "New step is tap step (Currently tap step)")
            startStep()
        }
    }
    /*
    updateLabelsWithData Specification
    Pre-Condition: None
    Post-Condition: Updates the labels in the view with the current steps data
     */
    fun updateLabelsWithData() {
        val step = getCurrentStep()
        stepTitle.text = step?.stepTitle
        stepDescription.text = step?.instruction
        exerciseName.text = ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.exerciseName
    }
    /*
        getCurrentStep Specification
        Provides a shortcut to the current step so no need to navigate through the objects
        Pre-Condition: None
        Post-Condition: Returns null (if no current step) or the current step

         */
    fun getCurrentStep(): ExerciseStep? {
        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }
    /*
    swipeRight/swipeLeft Specifications
    Goes to the previous or next step respectively
    Pre-Condition: None
    Post-Condition: Changes the current step and updates the UI
     */
    fun swipeRight() {
        ExercisePlayerObject.exercise.goToPreviousStep()
//        val text = getCurrentStep()
        changeStep()
    }
    fun swipeLeft() {
        ExercisePlayerObject.exercise.goToNextStep()
//        val test = getCurrentStep()
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
                if (Math.abs(diffX) > 100) { // 100 is the movement threshold to recognize the swipe
                    if (diffX > 0) {
                        // right swipe
                        this@ExercisePlayerTapViewActivity.swipeRight()
                    } else {
                        // left swipe
                        this@ExercisePlayerTapViewActivity.swipeLeft()
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