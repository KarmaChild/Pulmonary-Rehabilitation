package com.example.pulmonaryrehabilitation.model_since_2_17

interface Member {
    var id: String
    var username: String
    var password: String
    var email: String
    var stepGoal: Int
    var gamificationHistory: Map<String, GamificationHistoryClass>
    var usageHistory: Map<String, UsageHistoryClass>
    var stepHistory: Map<String, StepHistoryClass>

    override fun toString(): String
    fun toMemberMap(): Map<String, Any>
}