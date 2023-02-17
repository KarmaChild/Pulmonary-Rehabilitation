package com.example.pulmonaryrehabilitation.model_since_2_17

interface Member {
    var id: String
    var username: String
    var password: String
    var email: String
    var medicalType: String
//    var stepGoal: Int
//    var gamificationHistory: Map<String,Any>
//    var usageHistory: Map<String,Any>
//    var stepHistory: Map<String, Any>?

    override fun toString(): String
    fun toMemberMap(): Map<String, Any>
}