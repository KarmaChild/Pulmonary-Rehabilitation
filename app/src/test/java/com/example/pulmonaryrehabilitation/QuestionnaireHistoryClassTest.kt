package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.member.QuestionnaireHistoryClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class QuestionnaireHistoryClassTest {

    @Test
    fun questionnaireHistoryToStringTest() {
        val test = QuestionnaireHistoryClass("question", "answer")
        Assert.assertEquals(
            "{question='question', answer='answer'}",
            test.toString()
        )
    }

    @Test
    fun toQuestionnaireHistoryMapTest() {
        val test = QuestionnaireHistoryClass("question", "answer")
        val testMap = mutableMapOf<String, Any>("question" to "question", "answer" to "answer")
        Assert.assertEquals(testMap, test.toQuestionnaireHistoryMap())
    }
}