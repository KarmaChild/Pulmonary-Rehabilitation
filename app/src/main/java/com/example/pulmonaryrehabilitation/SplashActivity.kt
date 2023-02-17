package com.example.pulmonaryrehabilitation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.model_database.DatabaseMethod
import com.example.pulmonaryrehabilitation.model_since_2_17.ModelObject
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // hide action bar
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        // write to database
        val defaultMember = ModelObject.defaultMember()
        DatabaseMethod().writeToDatabase("Member", database, defaultMember)

        val defaultExerciseData = ModelObject.defaultExerciseData()
//        DatabaseMethod().writeToDatabase("ExerciseData", database, defaultExerciseData)

        // delete from database
//        DatabaseMethod().deleteFromDatabase("Member", database, "0")

        // read from database
        DatabaseMethod().readFromDatabase("Member", database)

//        // transitioning from splashActivity to EmailLoginActivity with 2000ms delayed
//        Handler().postDelayed({
//            startActivity(Intent(this, ExercisePlayerViewActivity::class.java))
//        }, 2000)
    }
}