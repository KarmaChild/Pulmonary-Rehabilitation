package com.example.pulmonaryrehabilitation.model_since_2_17

// create default objects with predefined properties
object ModelObject {

    fun defaultMember(): MemberClass {
        val defaultGamificationHistory = defaultGamificationHistory() as GamificationHistoryClass
        val defaultUsageHistory = defaultUsageHistory() as UsageHistoryClass
        val defaultStepHistory = defaultStepHistory() as StepHistoryClass
        return MemberClass(
            "0", "example@username", "example@password",
            "my@example.ca", 9000,
            mapOf("datetime" to defaultGamificationHistory, "datetime2" to defaultGamificationHistory),
            mapOf("datetime" to defaultUsageHistory, "datetime2" to defaultUsageHistory),
            mapOf(
                "datetime" to defaultStepHistory, "datetime3" to defaultStepHistory,
                "datetime5" to defaultStepHistory
            )
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

    fun defaultStepHistory(): StepHistory {
        return StepHistoryClass("499", "404")
    }
}