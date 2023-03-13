package com.example.pulmonaryrehabilitation.activity.testing

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.activity.exerciseplayeractivity.ExercisePlayerTapViewActivity
import com.example.pulmonaryrehabilitation.activity.login.EmailLoginActivity
import com.example.pulmonaryrehabilitation.activity.login.EmailRegisterActivity
import com.example.pulmonaryrehabilitation.activity.streaks.StreakActivity

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        val dashboardButton = findViewById<Button>(R.id.gotoDashboard)
        val loginButton = findViewById<Button>(R.id.gotoLogin)
        val registerButton = findViewById<Button>(R.id.gotoRegister)
        val exerciseButton = findViewById<Button>(R.id.goToExercisePlayer)
        val streakButton = findViewById<Button>(R.id.gotoStreaksPage)

        dashboardButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity :: class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity :: class.java)
            startActivity(intent)
            finish()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, EmailRegisterActivity :: class.java)
            startActivity(intent)
            finish()
        }

        streakButton.setOnClickListener {
            val intent = Intent(this, StreakActivity :: class.java)
            startActivity(intent)
            finish()
        }
        exerciseButton.setOnClickListener {
//            ExercisePlayerObject.addExerciseCollection() // for testing
            val intent = Intent(this, ExercisePlayerTapViewActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }
}