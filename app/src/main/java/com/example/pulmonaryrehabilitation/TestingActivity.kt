package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        val dashboardButton = findViewById<Button>(R.id.gotoDashboard)
        val loginButton = findViewById<Button>(R.id.gotoLogin)
        val registerButton = findViewById<Button>(R.id.gotoRegister)

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
    }
}