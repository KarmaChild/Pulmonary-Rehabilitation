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

//    override fun iterateGamificationHistoryMap(map: Map<String, GamificationHistoryClass>): String {
//        var gamificationHistoryContent = "{"
//        map.forEach { entry ->
//            gamificationHistoryContent += entry.key + ":" + entry.value.toString() + ","
//        }
//        gamificationHistoryContent += "}"
//        return gamificationHistoryContent
//    }
//
//    override fun iterateUsageHistoryMap(map: Map<String, UsageHistoryClass>): String {
//        var usageHistoryContent = "{"
//        map.forEach { entry ->
//            usageHistoryContent += entry.key + ":" + entry.value.toString() + ","
//        }
//        usageHistoryContent += "}"
//        return usageHistoryContent
//    }
//
//    override fun iterateStepHistoryMap(map: Map<String, StepHistoryClass>): String {
//        var stepHistoryContent = "{"
//        map.forEach { entry ->
//            stepHistoryContent += entry.key + ":" + entry.value.toString() + ","
//        }
//        stepHistoryContent += "}"
//        return stepHistoryContent
//    }

    @Exclude
    override fun toMemberMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "username" to username,
            "password" to password,
            "email" to email,
            "stepGoal" to stepGoal,
            "gamificationHistory" to gamificationHistory,
            "usageHistory" to usageHistory,
            "stepHistory" to stepHistory
        )
        // "gamificationHistory" to iterateGamificationHistoryMap(gamificationHistory),
        //            "usageHistory" to iterateUsageHistoryMap(usageHistory),
        //            "stepHistory" to iterateStepHistoryMap(stepHistory),
    }
}