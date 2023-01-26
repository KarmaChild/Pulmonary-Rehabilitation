package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailLoginActivity : AppCompatActivity() {
    var email = ""
    var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.LogIn)

        loginButton.setOnClickListener {
//          todo: code login logic here
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
//            successfully get input from email and password from front end (need authentication from back end)
            Toast.makeText(this, email, Toast.LENGTH_LONG).show()
            Toast.makeText(this, password, Toast.LENGTH_LONG).show()
        }
    }
}