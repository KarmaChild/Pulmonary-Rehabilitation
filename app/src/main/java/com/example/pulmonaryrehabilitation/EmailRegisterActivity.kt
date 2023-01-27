package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                username.isNotEmpty() && phone.isNotEmpty()
            ) {
                if (password == confirmPassword) {
                    // creates an instance and create a register user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener { task ->
                                // If registration was successfully done
                                if (task.isSuccessful) {
                                    // Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@EmailRegisterActivity,
                                        "You are registered successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // register is successful takes user to main menu
                                    val intent = Intent(
                                        this@EmailRegisterActivity,
                                        MainActivity :: class.java
                                    )
                                    // gets rid of extra layer of activities in stack
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    // If registration was not successful shows error message
                                    Toast.makeText(
                                        this@EmailRegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                } else {
                    Toast.makeText(
                        this, "Password is not matching",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this, "Empty fields are not allowed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}