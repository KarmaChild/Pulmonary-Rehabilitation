package com.example.pulmonaryrehabilitation.model_since_2_17

import com.google.firebase.database.Exclude

class QuestionnaireHistoryClass(
    override var question: String,
    override var answer: String
) : QuestionnaireHistory {
    override fun toString(): String {
        return "{question='$question', answer='$answer'}"
    }

    @Exclude
    fun toQuestionnaireHistoryMap(): Map<String, Any> {
        return mapOf(
            "question" to question,
            "answer" to answer
        )
    }
}