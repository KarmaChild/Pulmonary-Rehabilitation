package com.example.pulmonaryrehabilitation.member

import com.google.firebase.database.Exclude

class QuestionnaireHistoryClass(
    override var question: String,
    override var answer: String
) : QuestionnaireHistory {
    override fun toString(): String {
        return "{question='$question', answer='$answer'}"
    }

    @Exclude
    override fun toQuestionnaireHistoryMap(): Map<String, Any> {
        return mapOf(
            "question" to question,
            "answer" to answer
        )
    }
}