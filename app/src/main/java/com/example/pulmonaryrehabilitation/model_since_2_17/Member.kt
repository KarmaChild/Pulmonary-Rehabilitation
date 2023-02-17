package com.example.pulmonaryrehabilitation.model_since_2_17

interface Member {
    var id: String
    var username: String
    var password: String
    var email: String
    var medicalType: String
    var stepHistory: String

    override fun toString(): String
    fun toMemberMap(): Map<String, Any>
}