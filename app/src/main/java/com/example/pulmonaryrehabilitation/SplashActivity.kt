package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.model_database.DatabaseMethod
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
//        val defaultMember = ModelObject.defaultMember()
// // //        var testMap: Map<String, GamificationHistoryClass> = mapOf("datetime" to ModelObject.defaultGamificationHistory(), "datetime2" to ModelObject.defaultGamificationHistory())
// // //        var iterateMap = defaultMember.iterateGamificationHistoryMap(testMap)
// // //        Log.d("Map gamification", "")
// //        Log.d("defaultMemberJSON", defaultMember.toMemberMap().toString())
//        DatabaseMethod().writeToDatabase("Member", database, defaultMember)

//        val defaultExerciseData = ModelObject.defaultExerciseData()
//        DatabaseMethod().writeToDatabase("ExerciseData", database, defaultExerciseData)

        // delete from database
//        DatabaseMethod().deleteFromDatabase("Member", database, "0")

        // read from database
//        DatabaseMethod().readFromDatabase("Member", database)

//        // transitioning from splashActivity to EmailLoginActivity with 2000ms delayed
        Handler().postDelayed({
            startActivity(Intent(this, TestingActivity::class.java))
        }, 2000)

        // Ian's testing below
        DatabaseMethod().getUserDataFor("1", database)
    }
}