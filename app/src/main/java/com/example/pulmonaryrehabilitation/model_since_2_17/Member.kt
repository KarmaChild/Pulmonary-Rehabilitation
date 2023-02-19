package com.example.pulmonaryrehabilitation.model_since_2_17

interface Member {
    var id: String
    var isAdmin: Boolean
    var firstName: String
    var lastName: String
    var username: String
//    var password: String
    var email: String
    var stepGoal: Int
    var gamificationHistory: Map<String, GamificationHistoryClass>
    var usageHistory: Map<String, UsageHistoryClass>
    var stepHistory: Map<String, StepHistoryClass>
    var questionnaireHistory: Map<String, QuestionnaireHistoryClass>

    override fun toString(): String
//    fun iterateGamificationHistoryMap(map: Map<String, GamificationHistoryClass>): String
//    fun iterateUsageHistoryMap(map: Map<String, UsageHistoryClass>): String
//    fun iterateStepHistoryMap(map: Map<String, StepHistoryClass>): String

    fun toMemberMap(): Map<String, Any>
}