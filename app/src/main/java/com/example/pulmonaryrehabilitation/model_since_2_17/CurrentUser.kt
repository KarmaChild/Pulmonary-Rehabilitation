package com.example.pulmonaryrehabilitation.model_since_2_17

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
}