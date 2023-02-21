package com.example.pulmonaryrehabilitation.model_since_2_17

import android.util.Log
import com.example.pulmonaryrehabilitation.model_database.DatabaseMethod

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
    private val logTag: String = "CurrentUser"

    /*
    setData Method Specification
    Pre-Condition:
        Pass in a nullable Member Class. Other than testing, this will only be done via the method
        getUserDataFor() in the DatabaseMethod file.
    Post-Condition
        Sets the users data in the CurrentUser object.
     */
    fun setData(member: MemberClass?) {
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
        Log.d(logTag, "getUserId invoked")
        return data?.id ?: "Error"
    }
    fun getFirstName(): String {
        Log.d(logTag, "getUserId invoked")
        return data?.firstName ?: "Error"
    }
    fun getLastName(): String {

        return data?.lastName ?: "Error"
    }
    fun getStepGoal(): Int {
        return data?.stepGoal ?: -9999
    }
    fun getStepHistory(): Map<String, StepHistoryClass> {
        return data?.stepHistory ?: mutableMapOf()
    }
    fun getGamificationHistory(): Map<String, GamificationHistoryClass> {
        return data?.gamificationHistory ?: mutableMapOf()
    }
    fun getUsageHistory(): Map<String, UsageHistoryClass> {
        return data?.usageHistory ?: mutableMapOf()
    }
    fun getQuestionnaireHistory(): Map<String, QuestionnaireHistoryClass> {
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

     TODO: create a function that creates a timestap and replace the hard coded 'Timestamp' string
     TODO: Once we can append to Firebase objects the collection write functions need to be
      updated to only send the new value

     */
    fun setFirstName(newName: String) {
        if (data != null) {
            data?.firstName = newName
            DatabaseMethod().updateFirstNameFor(data!!.id, newName)
        }
    }

    fun setLastName(newName: String) {
        if (data != null) {
            data?.lastName = newName
            DatabaseMethod().updateLastNameFor(data!!.id, newName)
        }
    }
    fun setAdminStatus(newStatus: Boolean) {
        if (data != null) {
            data?.isAdmin = newStatus
            DatabaseMethod().updateAdminStatusFor(data!!.id, newStatus)
        }
    }
    fun setGoal(newGoal: Int) {
        if (data != null) {
            data?.stepGoal = newGoal
            DatabaseMethod().updateStepCountGoalFor(data!!.id, newGoal)
        }
    }
    fun addStepHistory(numberSteps: Int) {
        if (data != null) {
            data!!.stepHistory.put("Timestamp", StepHistoryClass(numberSteps.toString(), ""))
            DatabaseMethod().updateStepHistoryFor(data!!.id, data!!.stepHistory)
        }
    }
    fun addQuestionnaireHistory(question: String, answer: String) {
        if (data != null) {
            data!!.questionnaireHistory.put("Timestamp", QuestionnaireHistoryClass(question, answer))
            DatabaseMethod().updateQuestionnaireHistoryFor(data!!.id, data!!.questionnaireHistory)
        }
    }
    fun addUsageHistory(exerciseDone: String) {
        if (data != null) {
            data!!.usageHistory.put("Timestamp", UsageHistoryClass(exerciseDone, ""))
            DatabaseMethod().updateUsageHistoryFor(data!!.id, data!!.usageHistory)
        }
    }
    fun addGamificationHistory(event: String, points: String) {
        if (data != null) {
            data!!.gamificationHistory.put("Timestamp", GamificationHistoryClass(event, points))
            DatabaseMethod().updateGamificationHistory(data!!.id, data!!.gamificationHistory)
        }
    }

    // END SETTERS
}