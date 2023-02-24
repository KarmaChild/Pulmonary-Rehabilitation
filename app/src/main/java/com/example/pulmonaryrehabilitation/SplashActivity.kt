package com.example.pulmonaryrehabilitation
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

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

        // transitioning from splashActivity to EmailLoginActivity with 2000ms delayed
        Handler().postDelayed({
            startActivity(Intent(this, EmailLoginActivity::class.java))
        }, 2000)

        // Ian's testing below
        // DatabaseMethod().getUserDataFor("1", database)
//        val test1 = mutableMapOf(CurrentUser.getCurrentDateTime() to ModelObject.defaultStepHistory())
//        DatabaseMethod().updateStepHistoryFor("1", test1)
//
//        val test2 = mutableMapOf(CurrentUser.getCurrentDateTime() to ModelObject.defaultGamificationHistory())
//        DatabaseMethod().updateGamificationHistory("1", test2)
//
//        val test3 = mutableMapOf(CurrentUser.getCurrentDateTime() to ModelObject.defaultUsageHistory())
//        DatabaseMethod().updateUsageHistoryFor("1", test3)
    }
}