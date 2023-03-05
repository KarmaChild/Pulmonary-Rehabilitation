package com.example.pulmonaryrehabilitation.model

class StepHistoryClass(
    override var stepCount: String,

) : StepHistory {
    override fun toString(): String {
        return "{stepCount='$stepCount'}"
    }
}