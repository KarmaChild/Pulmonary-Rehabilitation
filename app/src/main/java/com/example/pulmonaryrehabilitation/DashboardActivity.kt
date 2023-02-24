package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.model_since_2_17.CurrentUser

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        val currentUserId = CurrentUser.getUserId()
        Log.d("DashBoardActivity", currentUserId)

        setContentView(R.layout.activity_dashboard)
        // show users steps and streaks
        initializeStepCounter()
        initializeStreaks()
    }

    /**
     * For now this function Just displays step Goals
     * Later On it will display the step Counter
     */
    fun initializeStepCounter() {
        val stepDisplay = findViewById<TextView>(R.id.stepDisplayText)
        val userStepGoals: String = CurrentUser.getStepGoal().toString()
        stepDisplay.text = userStepGoals
    }

    /**
     * For now This function displays step goals as well
     *  Later on when implemented it will display the users streaks
     */
    fun initializeStreaks() {
        val streakDisplay = findViewById<TextView>(R.id.streakNumber)
        val userStreaks: String = CurrentUser.getStepGoal().toString()
        streakDisplay.text = userStreaks
    }
}