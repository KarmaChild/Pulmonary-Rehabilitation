package com.example.pulmonaryrehabilitation.model_since_2_17

class StepHistoryClass(
    override var stepCount: String,

) : StepHistory {
    override fun toString(): String {
        return "{stepCount='$stepCount'}"
    }
}