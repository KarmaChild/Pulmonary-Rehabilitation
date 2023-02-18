package com.example.pulmonaryrehabilitation.model_since_2_17

// create default objects with predefined properties
object ModelObject {

    fun defaultMember(): Member {
        val defaultGamificationHistory = defaultGamificationHistory() as GamificationHistoryClass
        val defaultUsageHistory = defaultUsageHistory() as UsageHistoryClass
        val defaultStepHistory = defaultStepHistory() as StepHistoryClass

        return MemberClass(
            "0", "example@username", "example@password",
            "my@example.ca", 9000,
            mapOf("datetime" to defaultGamificationHistory),
            mapOf("datetime" to defaultUsageHistory),
            mapOf("datetime" to defaultStepHistory)
        )
    }

    fun defaultExerciseData(): ExerciseData {
        return ExerciseDataClass("0", "0", "0", "0", "0")
    }

    fun defaultGamificationHistory(): GamificationHistory {
        return GamificationHistoryClass("499", "404")
    }

    fun defaultUsageHistory(): UsageHistory {
        return UsageHistoryClass("499", "404")
    }

    fun defaultStepHistory(): StepHistory {
        return StepHistoryClass("499", "404")
    }
}