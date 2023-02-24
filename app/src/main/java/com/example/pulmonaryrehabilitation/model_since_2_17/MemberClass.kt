package com.example.pulmonaryrehabilitation.model_since_2_17

import com.google.firebase.database.Exclude

// create Member class to read from and write to the database
class MemberClass(
    override var id: String = "",
    override var isAdmin: Boolean = false,
    override var firstName: String = "",
    override var lastName: String = "",
    override var username: String = "",
    override var email: String = "",
    override var stepGoal: Int,
    override var gamificationHistory: MutableMap<String, GamificationHistoryClass>,
    override var usageHistory: MutableMap<String, UsageHistoryClass>,
    override var stepHistory: MutableMap<String, StepHistoryClass>,
    override var questionnaireHistory: MutableMap<String, QuestionnaireHistoryClass>
) : Member {

    override fun toString(): String {
        // TODO update this
        return "MemberClass(id='$id', username='$username'," +
            " email='$email', gamificationHistory=$gamificationHistory, " +
            "usageHistory=$usageHistory, stepHistory=$stepHistory) ," +
            "questionnaireHistory=$questionnaireHistory"
    }

    @Exclude
    override fun toMemberMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "isAdmin" to isAdmin,
            "firstName" to firstName,
            "lastName" to lastName,
            "username" to username,
            "email" to email,
            "stepGoal" to stepGoal,
            "gamificationHistory" to gamificationHistory,
//            "usageHistory" to usageHistory,
//            "stepHistory" to stepHistory,
//            "questionnaireHistory" to questionnaireHistory
        )
    }
}