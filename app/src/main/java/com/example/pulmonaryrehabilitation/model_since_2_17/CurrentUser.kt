package com.example.pulmonaryrehabilitation.model_since_2_17

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
//        setGoal(3333)
//        addStepHistory(4999)
    }

    fun getFirstName(): String {
        return data?.firstName ?: "Error"
    }
    fun getLastName(): String {
        return data?.lastName ?: "Error"
    }
    fun getStepGoal(): Int {
        return data?.stepGoal ?: -9999
    }
    fun getStepHistory(): Map<String, StepHistoryClass> {
        return data?.stepHistory ?: mapOf()
    }
    fun getGamificationHistory(): Map<String, GamificationHistoryClass> {
        return data?.gamificationHistory ?: mapOf()
    }
    fun getUsageHistory(): Map<String, UsageHistoryClass> {
        return data?.usageHistory ?: mapOf()
    }
    fun getQuestionnaireHistory(): Map<String, QuestionnaireHistoryClass> {
        return data?.questionnaireHistory ?: mapOf()
    }
    // note for below, it's safe to use !! because I check it's not null
    // also it updates the local object without calling the database to avoid an unnecessary read
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
            print("")
        }
    }
    fun addQuestionnaireHistory(question: String, answer: String) {
        if (data != null) {
            data!!.questionnaireHistory.put("Timestamp", QuestionnaireHistoryClass(question, answer))
            DatabaseMethod().updateQuestionnaireHistoryFor(data!!.id, data!!.questionnaireHistory)
            print("")
        }
    }
    fun addUsageHistory(exerciseDone: String) {
        if (data != null) {
            data!!.usageHistory.put("Timestamp", UsageHistoryClass(exerciseDone, ""))
            DatabaseMethod().updateUsageHistoryFor(data!!.id, data!!.usageHistory)
            print("")
        }
    }
    fun addGamificationHistory(event: String, points: String) {
        if (data != null) {
            data!!.gamificationHistory.put("Timestamp", GamificationHistoryClass(event, points))
            DatabaseMethod().updateGamificationHistory(data!!.id, data!!.gamificationHistory)
            print("")
        }
    }
}