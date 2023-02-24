package com.example.pulmonaryrehabilitation.model_since_2_17

interface QuestionnaireHistory {
    var question: String
    var answer: String
    override fun toString(): String

    fun toQuestionnaireHistoryMap(): Map<String, Any>
}