package com.example.pulmonaryrehabilitation.model_since_2_17

import android.util.Log
import com.example.pulmonaryrehabilitation.model_database.DatabaseMethod
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.util.concurrent.TimeUnit

/*
CurrentUser Object Specification
This is the connection to the users data. Anything you want to get or change, should be done through this.
It is a singleton because of the nature of need. We will need it on almost every activity and so passing it around
seems like a poor implementation.
Pre-Condition:
getUserDataFor() in Database method needs to be called on a valid user ID otherwise everything will return null
Post-Condition:
Allow developers access to the relevant user data through internal methods.

Ian Kohlert
Feb 19, 2023
 */
object CurrentUser {
    private var data: MemberClass? = null
    // When searching the logs, "CurrentUser" tag can be used to filter the logs of this class
    private const val LOG_TAG: String = "CurrentUser"

    /*
    setData Method Specification
    Pre-Condition:
        Pass in a nullable Member Class. Other than testing, this will only be done via the method
        getUserDataFor() in the DatabaseMethod file.
    Post-Condition
        Sets the users data in the CurrentUser object.
     */
    fun setData(member: MemberClass?) {
        Log.d(LOG_TAG, "setData() invoked")
        data = member
        // Testing ↓
//        setGoal(3333)
//        addStepHistory(4999)
    }

    // GETTERS
    /* Specification for each getter
        Pre-Condition: None
        Post-Condition:
            It will return the desired value if 'data' has been set with a valid memberClass object
            If not it returns a default value via the elvis operator (?:)
            The goal of the default value is to make it clear that there was an error

     */

    fun getUserId(): String {
        Log.d(LOG_TAG, "getUserId() invoked")
        val user = Firebase.auth.currentUser
        return if (user != null) {
            user.uid
        } else {
            // user not signed in
            Log.e(LOG_TAG, "user not signed in")
            "Error"
        }
    }
    fun getFirstName(): String {
        Log.d(LOG_TAG, "getFirstName() invoked")
        return data?.firstName ?: "Error"
    }
    fun getLastName(): String {
        Log.d(LOG_TAG, "getLastName() invoked")
        return data?.lastName ?: "Error"
    }
    fun getStepGoal(): Int {
        Log.d(LOG_TAG, "getStepGoal() invoked")
        return data?.stepGoal ?: -9999
    }
    fun getStepHistory(): Map<String, StepHistoryClass> {
        Log.d(LOG_TAG, "getStepHistory() invoked")
        return data?.stepHistory ?: mutableMapOf()
    }
    fun getGamificationHistory(): Map<String, GamificationHistoryClass> {
        Log.d(LOG_TAG, "getGamificationHistory() invoked")
        return data?.gamificationHistory ?: mutableMapOf()
    }
    fun getUsageHistory(): Map<String, UsageHistoryClass> {
        Log.d(LOG_TAG, "getUsageHistory() invoked")
        return data?.usageHistory ?: mutableMapOf()
    }

    /*
    Specification for getCurrentDateTime
        Pre-Condition: None
        Post-Condition:
            It will return the current UTC time in "yyyy-MM-dd HH:mm:ss:SSSSSS" format as a String
     */
    fun getCurrentDateTime(): String {
        Log.d(LOG_TAG, "getCurrentDateTime() invoked")

        return Instant.now().toEpochMilli().toString()
    }

    fun getLastQuestionnaireDate(): Long? {
        Log.d(LOG_TAG, "getLastQuestionnaireDate() invoked")

        return data?.lastQuestionnaireDate?.toLong()
    }

    fun daysSinceLastQuestionnaire(lastQuestionnaireDate: Long?, daysSince: Long): Boolean {
        val currentTime: Long = getCurrentDateTime().toLong()
        val daysToSubtract = TimeUnit.DAYS.toMillis(daysSince)

        return (currentTime - daysToSubtract) > lastQuestionnaireDate!!
    }

    fun parseDate(date: Long) {
        Log.d(LOG_TAG, "parseDate() invoked")

        // TODO
    }
    fun getQuestionnaireHistory(): Map<String, QuestionnaireHistoryClass> {
        Log.d(LOG_TAG, "getQuestionnaireHistory() invoked")
        return data?.questionnaireHistory ?: mutableMapOf()
    }
    // END GETTERS

    // SETTERS
    /*
    Specification for each setter
    Pre-Condition:
        The new value you wish to add to the database
    Post-Condition:
        Updates the local 'data' object to avoid an unnecessary read
        Updates the Firebase database

     // note for below, it's safe to use !! because I check it's not null

     TODO: create a function that creates a timestamp and replace the hard coded 'Timestamp' string
     TODO: Once we can append to Firebase objects the collection write functions need to be
      updated to only send the new value

     */
    fun setFirstName(newName: String) {
        Log.d(LOG_TAG, "setFirstName() invoked")
        if (data != null) {
            data?.firstName = newName
            DatabaseMethod().updateFirstNameFor(data!!.id, newName)
        }
    }

    fun setLastName(newName: String) {
        Log.d(LOG_TAG, "setLastName() invoked")
        if (data != null) {
            data?.lastName = newName
            DatabaseMethod().updateLastNameFor(data!!.id, newName)
        }
    }
    fun setAdminStatus(newStatus: Boolean) {
        Log.d(LOG_TAG, "setAdminStatus() invoked")
        if (data != null) {
            data?.isAdmin = newStatus
            DatabaseMethod().updateAdminStatusFor(data!!.id, newStatus)
        }
    }
    fun setGoal(newGoal: Int) {
        Log.d(LOG_TAG, "setGoal() invoked")
        if (data != null) {
            data?.stepGoal = newGoal
            DatabaseMethod().updateStepCountGoalFor(data!!.id, newGoal)
        }
    }
    fun addStepHistory(numberSteps: Int) {
        Log.d(LOG_TAG, "addStepHistory() invoked")
        if (data != null) {
            val timestamp: String = getCurrentDateTime()
            // update our local map for current user
            data!!.stepHistory[timestamp] = StepHistoryClass(numberSteps.toString())
            // update database step value for current user
            val newHistory = mutableMapOf(timestamp to StepHistoryClass(numberSteps.toString()))
            DatabaseMethod().updateStepHistoryFor(data!!.id, newHistory)
        }
    }
    fun addQuestionnaireHistory(question: String, answer: String) {
        Log.d(LOG_TAG, "addQuestionnaireHistory() invoked")
        val timestamp: String = getCurrentDateTime()
        if (data != null) {
            // update our local map for current user
            data!!.questionnaireHistory[timestamp] =
                QuestionnaireHistoryClass(question, answer)
            data!!.lastQuestionnaireDate = timestamp
            // update database step value for current user
            val newHistory = mutableMapOf(timestamp to QuestionnaireHistoryClass(question, answer))
            DatabaseMethod().updateQuestionnaireHistoryFor(data!!.id, newHistory)
        }
    }

    // Currently adding 2 items to usage history. Will need to redo this after we get more
    // info about usage from the stakeholder
    fun addUsageHistory(exerciseDone: String) {
        Log.d(LOG_TAG, "addUsageHistory() invoked")
        val timestamp: String = getCurrentDateTime()
        if (data != null) {
            data!!.usageHistory[timestamp] = UsageHistoryClass(exerciseDone, "item2")
            val newHistory = mutableMapOf(timestamp to UsageHistoryClass(exerciseDone, "item2"))
            DatabaseMethod().updateUsageHistoryFor(data!!.id, newHistory)
        }
    }
    fun addGamificationHistory(event: String, points: String) {
        Log.d(LOG_TAG, "addGamificationHistory() invoked")
        if (data != null) {
            val timestamp: String = getCurrentDateTime()
            data!!.gamificationHistory[timestamp] = GamificationHistoryClass(event, points)
            DatabaseMethod().updateGamificationHistory(data!!.id, data!!.gamificationHistory)
        }
    }

    // END SETTERS
}