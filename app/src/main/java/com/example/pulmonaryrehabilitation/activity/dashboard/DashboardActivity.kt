package com.example.pulmonaryrehabilitation.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.exerciseplayeractivity.ExercisePlayerTapViewActivity
import com.example.pulmonaryrehabilitation.member.CurrentUser

class DashboardActivity : AppCompatActivity() {
    private lateinit var toTapView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // hide action bar
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        toTapView = findViewById(R.id.toTapView)
        toTapView.setOnClickListener {
            val intent = Intent(this, ExercisePlayerTapViewActivity :: class.java)
            startActivity(intent)
            finish()
        }

        val currentUserId = CurrentUser.getUserId()
        Log.d("DashBoardActivity", currentUserId)

        // show users steps and streaks
        initializeStepCounter()
        initializeStreaks()
    }

    /**
     * For now this function Just displays step Goals
     * Later On it will display the step Counter
     */
    private fun initializeStepCounter() {
        val stepDisplay = findViewById<TextView>(R.id.stepDisplayText)
        val userStepGoals: String = CurrentUser.getStepGoal().toString()
        stepDisplay.text = userStepGoals
    }

    /**
     * For now This function displays step goals as well
     *  Later on when implemented it will display the users streaks
     */
    private fun initializeStreaks() {
        val streakDisplay = findViewById<TextView>(R.id.streakNumber)
        val userStreaks: String = CurrentUser.getStepGoal().toString()
        streakDisplay.text = userStreaks
    }
}