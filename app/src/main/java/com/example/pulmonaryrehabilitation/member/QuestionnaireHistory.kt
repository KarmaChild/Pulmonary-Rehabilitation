package com.example.pulmonaryrehabilitation.member

interface QuestionnaireHistory {
    var question: String
    var answer: String
    override fun toString(): String

    fun toQuestionnaireHistoryMap(): Map<String, Any>
}