package com.example.pulmonaryrehabilitation.model_since_2_17

class StepHistoryClass(
    override var itemname: String,
    override var itemname2: String
) : StepHistory {
    override fun toString(): String {
        return "{itemname='$itemname', itemname2='$itemname2'}"
    }
}