package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Exercises.Steps.TapStep
import com.example.pulmonaryrehabilitation.Exercises.Steps.TimerStep
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayers

class TempExercisePlayerActivity : AppCompatActivity() {
    enum class StepType {
        TIMER, TAP, NONE
    }
    val exercisePlayer = ExercisePlayers.testExercisePlayer1()
    var stepType: StepType = StepType.NONE
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ex_player)
        val continueButton = findViewById<Button>(R.id.continueButton)
        val previousButton = findViewById<Button>(R.id.previousButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        continueButton.setOnClickListener {
            continueTapped()
        }
        previousButton.setOnClickListener {
            previousTapped()
        }
        nextButton.setOnClickListener {
            nextTapped()
        }

        startCurrentStep()
    }

    // this displays the correct info/starts timer if needed
    fun startCurrentStep() {
        if (getCurrentExerciseName() != "Error") {
            displayCurrentData()
            if (stepType == StepType.TIMER) {
                startTimer(getTimerValue())
            }
        }
    }
    // timer for timer steps
    // goes to next step automatically
    // updates UI
    fun startTimer(time: Int) {
        var timeLeft = time
        timer = object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timerLabel = findViewById<TextView>(R.id.tempTimer)
                timerLabel.text = "$timeLeft"
                timeLeft -= 1
            }
            override fun onFinish() {
                exercisePlayer.goToNextStep()
                startCurrentStep()
            }
        }.start()
    }
    // for 'tap' type steps
    fun continueTapped() {
        if (stepType == StepType.TAP) {
            exercisePlayer.goToNextStep()
            startCurrentStep()
        }
    }
    //goes to the previous step
    // known bug when going from previous exercise, it has an error on tap one but then goes
    fun previousTapped() {
        exercisePlayer.goToPreviousStep()
        if (this::timer.isInitialized) {
            timer.cancel()
        }
        startCurrentStep()
    }
    fun nextTapped() {
        exercisePlayer.goToNextStep()
        if (this::timer.isInitialized) {
            timer.cancel()
        }
        startCurrentStep()
    }

    // displays the current exercise/step to user
    fun displayCurrentData() {
        // this get's the text views and populates them with the data for
        // the current collection, exercise, step
        val collectionName: TextView = findViewById<TextView>(R.id.tempCollection)
        val exerciseName = findViewById<TextView>(R.id.tempExercise)
        val stepName = findViewById<TextView>(R.id.tempStepName)
        val stepDescription = findViewById<TextView>(R.id.tempStepDesc)
        val stepTypeL = findViewById<TextView>(R.id.tempStepType)
        val timerLabel = findViewById<TextView>(R.id.tempTimer)
        collectionName.text = getCurrentCollectionName()
        exerciseName.text = getCurrentExerciseName()
        stepName.text = getCurrentStepName()
        stepDescription.text = getCurrentStepDescription()
        stepTypeL.text = getCurrentStepType()
        if (stepType == StepType.TIMER) {
            timerLabel.text = "${getTimerValue()}"
        } else {
            timerLabel.text = ""
        }
    }
    fun getCurrentCollectionName(): String {
        return exercisePlayer.exerciseRoutine.collectionName
    }
    fun getCurrentExerciseName(): String {
        return exercisePlayer.exerciseRoutine.getCurrentExercise()?.exerciseName ?: "Error"
        // The ?: "" returns a default if it's null
    }
    fun getCurrentStepName(): String {
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        return exercise?.getCurrentStep()?.stepTitle ?: "Error"
    }
    fun getCurrentStepDescription(): String {
        val routine = exercisePlayer.exerciseRoutine
        val exercise = routine?.getCurrentExercise()
        return exercise?.getCurrentStep()?.instruction ?: "Error"
    }

    fun getCurrentStepType(): String {
        val currentStep = exercisePlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
        if (currentStep is TapStep) {
            stepType = StepType.TAP
            return "Tap"
        } else if (currentStep is TimerStep) {
            stepType = StepType.TIMER
            return "Timer"
        } else {
            return "None"
        }
    }
    fun getTimerValue(): Int {
        val currentStep = exercisePlayer.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
        if (currentStep is TimerStep) {
            val step = currentStep as TimerStep
            return step.duration
        }
        return 0
    }
}