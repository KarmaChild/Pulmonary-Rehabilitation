package com.example.pulmonaryrehabilitation.model_since_2_17

import com.google.firebase.database.Exclude

class GamificationHistoryClass(
    override var event: String,
    override var points: String
) : GamificationHistory {
    override fun toString(): String {
        return "{event='$event', points='$points'}"
    }

    @Exclude
    fun toGamificationHistoryClassMap(): Map<String, Any> {
        return mapOf(
            "event" to event,
            "points" to points
        )
    }
}