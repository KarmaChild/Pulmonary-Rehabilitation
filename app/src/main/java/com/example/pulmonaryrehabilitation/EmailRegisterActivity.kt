package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailRegisterActivity : AppCompatActivity() {
    var email = ""
    var password = ""
    var username = ""
    var phone = ""
    var confirmPassword = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_register)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.register)

        registerButton.setOnClickListener {
//          todo: code login logic here
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
            confirmPassword = confirmPasswordEditText.text.toString()
            phone = phoneEditText.text.toString()
            username = usernameEditText.text.toString()
//            successfully get input from email and password from front end (need authentication from back end)
            Toast.makeText(this, email, Toast.LENGTH_LONG).show()
            Toast.makeText(this, username, Toast.LENGTH_LONG).show()
            Toast.makeText(this, phone, Toast.LENGTH_LONG).show()
            Toast.makeText(this, password, Toast.LENGTH_LONG).show()
            Toast.makeText(this, confirmPassword, Toast.LENGTH_LONG).show()
        }
    }
}