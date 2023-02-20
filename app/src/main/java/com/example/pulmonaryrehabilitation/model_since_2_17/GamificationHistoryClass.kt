package com.example.pulmonaryrehabilitation.model_since_2_17

class GamificationHistoryClass(
    override var itemname: String,
    override var itemname2: String
) : GamificationHistory {
    override fun toString(): String {
        return "{itemname='$itemname', itemname2='$itemname2'}"
    }
}