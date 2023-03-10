package com.example.pulmonaryrehabilitation.activity.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.member.CurrentUser

class DashboardActivity : AppCompatActivity() {
    lateinit var exerciseCollection: ImageView
    lateinit var streakTextView: TextView
    lateinit var stepCountTextView: TextView
    lateinit var stepGoalTextView: TextView
    lateinit var diseaseTextView: TextView
    lateinit var collectionNameTextView: TextView
    lateinit var collectionDescriptionTextView: TextView
    lateinit var collectionTimeTextView: TextView
    lateinit var collectionView: ConstraintLayout
    lateinit var messageTextView: TextView
    lateinit var menuImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        val currentUserId = CurrentUser.getUserId()
        Log.d("DashBoardActivity", currentUserId)

        setContentView(R.layout.activity_dashboard2)

        exerciseCollection = findViewById(R.id.greenBackground)
        exerciseCollection.setOnClickListener {
            goToExerciseCollection()
        }
        streakTextView = findViewById(R.id.dashboardStreakTextView)
        stepCountTextView = findViewById(R.id.dashboardStepsTextView)
        stepGoalTextView = findViewById(R.id.dashboardStepsGoalTextView)
        diseaseTextView = findViewById(R.id.dashboardDiseaseTextView)
        collectionNameTextView = findViewById(R.id.dashboardCollectionNameTextView)
        collectionDescriptionTextView = findViewById(R.id.dashboardCollectionDescription)
        collectionTimeTextView = findViewById(R.id.dashboardCollectionTime)
        messageTextView = findViewById(R.id.dashboardWelcomeMessage)
        menuImageView = findViewById(R.id.dashboardKebabMenu)
        menuImageView.setOnClickListener {
            goToMenu()
        }

        collectionView = findViewById(R.id.dashboardCollection)

        // show users steps and streaks
        initializeStepCounter()
        initializeStreaks()
        initializeCollection()
//        setCollectionVisibility(false)
    }

    /*
        This will need to grab the users name (to replace Human). If we stick with "good morning"
        we'll also need to grab the time for the correct greeting.
     */
    fun initializeWelcomeMessage() {
        messageTextView.text = "Good morning, Human"
    }

    /**
     * For now this function Just displays step Goals
     * Later On it will display the step Counter
     */
    fun initializeStepCounter() {
//        val stepDisplay = findViewById<TextView>(R.id.stepDisplayText)
        stepCountTextView.text = "1000"
        val userStepGoals: String = CurrentUser.getStepGoal().toString()
        stepGoalTextView.text = userStepGoals
    }

    /**
     * Display user streaks on Dashboard
     */
    fun initializeStreaks() {
        val userStreaks: String = CurrentUser.getStreak()
        streakTextView.text = userStreaks
    }

    /*
    Grab the collection information and populate the fields
     */
    fun initializeCollection() {
        diseaseTextView.text = "COPD"
        collectionNameTextView.text = "Week 2: Day 1"
        collectionDescriptionTextView.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed congue fermentum libero, et accumsan nisi laoreet in. Vestibulum tempor, quam vel ultrices ornare."
        collectionTimeTextView.text = "37m"
    }

    /*
    If there is no collection to do (for example they have already done one today) you can hide this
     */
    fun setCollectionVisibility(isVisible: Boolean) {
        collectionView.isVisible = isVisible
    }
    /*
        This will grab the exercise collection and send it to the exercise player
     */
    fun goToExerciseCollection() {
        println("Hello")
    }
    /*

     */
    fun goToMenu() {
        println("Menu")
    }
}