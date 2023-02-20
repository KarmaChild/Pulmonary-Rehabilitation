package com.example.pulmonaryrehabilitation.model_since_2_17

// create default objects with predefined properties
object ModelObject {

//    fun defaultMember(): MemberClass {
//        val defaultGamificationHistory = defaultGamificationHistory() as GamificationHistoryClass
//        val defaultUsageHistory = defaultUsageHistory() as UsageHistoryClass
//        val defaultStepHistory = defaultStepHistory() as StepHistoryClass
//        return MemberClass(
//            "0", "example@username", "example@password",
//            "my@example.ca", 9000,
//            mapOf("datetime" to defaultGamificationHistory, "datetime2" to defaultGamificationHistory),
//            mapOf("datetime" to defaultUsageHistory, "datetime2" to defaultUsageHistory),
//            mapOf(
//                "datetime" to defaultStepHistory, "datetime3" to defaultStepHistory,
//                "datetime5" to defaultStepHistory
//            )
//        )
//    }
    fun defaultMember(): MemberClass {
        return MemberClass(
            "1", true, "Georfe", "Scrunkle", "1", "example@admin.com", 5000, mutableMapOf("Feb 19 9am" to defaultGamificationHistory()),
            mutableMapOf("Feb 17 10am" to defaultUsageHistory(), "Feb 17 11am" to defaultUsageHistory()),
            mutableMapOf("Feb 17 11am" to defaultStepHistory()), mutableMapOf("Feb 16 10am" to defaultQuestionnaireHistory(), "Feb 19 33am" to defaultQuestionnaireHistory())
        )
    }

    fun defaultExerciseData(): ExerciseDataClass {
        return ExerciseDataClass("0", "0", "0", "0", "0")
    }

    fun defaultGamificationHistory(): GamificationHistoryClass {
        return GamificationHistoryClass("499", "404")
    }

    fun defaultUsageHistory(): UsageHistoryClass {
        return UsageHistoryClass("499", "404")
    }

    fun defaultStepHistory(): StepHistoryClass {
        return StepHistoryClass("499", "404")
    }
    fun defaultQuestionnaireHistory(): QuestionnaireHistoryClass {
        return QuestionnaireHistoryClass("I am a question", "I am their answer")
    }
}