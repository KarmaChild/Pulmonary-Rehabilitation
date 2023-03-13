package com.example.pulmonaryrehabilitation.member

class StepHistoryClass(
    override var stepCount: String,

) : StepHistory {
    override fun toString(): String {
        return "{stepCount='$stepCount'}"
    }
}