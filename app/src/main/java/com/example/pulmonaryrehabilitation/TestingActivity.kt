package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.exerciseplayer.ExercisePlayerObject

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        val dashboardButton = findViewById<Button>(R.id.gotoDashboard)
        val loginButton = findViewById<Button>(R.id.gotoLogin)
        val registerButton = findViewById<Button>(R.id.gotoRegister)
        val exerciseButtton = findViewById<Button>(R.id.goToExercisePlayer)

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
        exerciseButtton.setOnClickListener {
            ExercisePlayerObject.addExerciseCollection() // for testing
            val intent = Intent(this, ExercisePlayerTapViewActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }
}