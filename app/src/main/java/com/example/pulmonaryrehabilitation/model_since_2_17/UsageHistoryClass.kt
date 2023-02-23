package com.example.pulmonaryrehabilitation.model_since_2_17

class UsageHistoryClass(
    override var itemname: String
) : UsageHistory {
    override fun toString(): String {
        return "{itemname='$itemname'}"
    }
}