package com.example.pulmonaryrehabilitation.model_since_2_17

class QuestionnaireHistoryClass(
    override var question: String,
    override var answer: String
) : QuestionnaireHistory {
    override fun toString(): String {
        return "{question='$question', answer='$answer'}"
    }
}