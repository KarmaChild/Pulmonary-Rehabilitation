package com.example.pulmonaryrehabilitation.model

class UsageHistoryClass(
    override var itemname: String,
    override var itemname2: String
) : UsageHistory {
    override fun toString(): String {
        return "{itemname='$itemname', itemname2='$itemname2'}"
    }
}