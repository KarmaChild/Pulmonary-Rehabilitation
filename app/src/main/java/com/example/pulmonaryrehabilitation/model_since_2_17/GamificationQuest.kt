package com.example.pulmonaryrehabilitation.model_since_2_17

interface GamificationQuest {
    var id: String
    var question: String

    // we can create 4 (String) properties for answers here for simplicity,
    // Or we can create 1 (Set) property to store these answers
    var answer1: String
    var answer2: String
    var answer3: String
    var answer4: String
}