package com.example.pulmonaryrehabilitation.activity.streaks

data class StreakBadgeData(
    // storing the badge name, description and its image.
    // Storing the badge unlock value
    val badgeName: String,
    val badgeDescription: String,
    val badgeIcon: Int,
    val badgeUnlockValue: Int
)
