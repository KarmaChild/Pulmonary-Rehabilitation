package com.example.pulmonaryrehabilitation.model_since_2_17

interface Gamification {
    var watchingPoint: String // point per video
    var maxPointPerDay: String // max point can a member get in one day
    var minVideoToStreak: String // min video a member must watch per day to maintain streak
    var watchingStreak: String // number of days on streak
    var questionairy: String // multiple choice questions and answers -> plan to call id here
}