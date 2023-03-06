package com.example.pulmonaryrehabilitation.activity.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.SplashActivity
import com.example.pulmonaryrehabilitation.model.MemberClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EmailRegisterActivity : AppCompatActivity() {

    private var email = ""
    private var password = ""
    private var username = ""
    private var confirmPassword = ""
    private val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_register)
        // hide action bar
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        // variables achieved from user input
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.register)
        val login = findViewById<TextView>(R.id.login)

        // transitioning from EmailRegisterActivity to EmailLoginActivity when click log in
        login.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity :: class.java)
            startActivity(intent)
            finish()
        }

        // send user's inputs to register to the database
        registerButton.setOnClickListener {
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
            confirmPassword = confirmPasswordEditText.text.toString()
            username = usernameEditText.text.toString()

//            successfully get input from email and password from front end (need authentication from back end)
            registerMember(email, password, confirmPassword, username)
        }
    }

    // passed manual testing
    // check for user's inputs, add to the database if all email is valid
    private fun registerMember(
        email: String,
        password: String,
        confirmPassword: String,
        username: String,
    ) {
        if (email.isNotEmpty() && password.isNotEmpty() &&
            confirmPassword.isNotEmpty() &&
            username.isNotEmpty()
        ) {
            if (password == confirmPassword) {
                // creates an instance and create a register user with email and password
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        // If registration was successfully done
                        if (task.isSuccessful) {
                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this, "Successfully Registered",
                                Toast.LENGTH_SHORT
                            ).show()

                            // add user data to firebase realtime database
                            val myRef = database.getReference("Member")

                            val mem = MemberClass(
                                firebaseUser.uid, false, null, null, username, email, null,
                                null,
                                null,
                                null,
                                null,
                                null
                            )

                            registerRealTimeMember(mem, myRef)

                            // register is successful takes user to splash activity then log in
                            val intent = Intent(this, SplashActivity::class.java)
                            // gets rid of extra layer of activities in stack
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                        } else {
                            // If registration was not successful shows error message
                            Toast.makeText(
                                this, "Register Error",
                                Toast.LENGTH_SHORT
                            ).show()
                            Toast.makeText(
                                this, "Email Existed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show()
        }
    }

    // passed manual testing
    // add member to realtime database
    private fun registerRealTimeMember(memberClass: MemberClass, myRef: DatabaseReference) {
        val key = memberClass.id // initialize key
        val values = memberClass.toMemberMap() // initialize value
        // put key and its value to hashmap
        val childUpdates = hashMapOf<String, Any>(
            key to values,
        )
        myRef.updateChildren(childUpdates)
    }
}