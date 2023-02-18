package com.example.pulmonaryrehabilitation.model_since_2_17

import com.google.firebase.database.Exclude

// create Member class to read from and write to the database
class MemberClass(
    override var id: String = "",
    override var username: String = "",
    override var password: String = "",
    override var email: String = "",
    override var stepGoal: Int,
    override var gamificationHistory: Map<String, GamificationHistoryClass>,
    override var usageHistory: Map<String, UsageHistoryClass>,
    override var stepHistory: Map<String, StepHistoryClass>,
) : Member {

    override fun toString(): String {
        return "MemberClass(id='$id', username='$username', password='$password'," +
            " email='$email', gamificationHistory=$gamificationHistory, " +
            "usageHistory=$usageHistory, stepHistory=$stepHistory)"
    }

    @Exclude
    override fun toMemberMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "username" to username,
            "password" to password,
            "email" to email,
            "stepGoal" to stepGoal,
            "gamificationHistory" to "{datetime:${gamificationHistory["datetime"]}}",
            "usageHistory" to "{datetime:${usageHistory["datetime"]}}",
            "stepHistory" to "{datetime:${stepHistory["datetime"]}}",
        )
    }
}