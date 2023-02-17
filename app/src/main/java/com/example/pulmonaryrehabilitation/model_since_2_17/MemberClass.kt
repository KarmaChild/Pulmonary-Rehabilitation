package com.example.pulmonaryrehabilitation.model_since_2_17

import com.google.firebase.database.Exclude

// create Member class to read from and write to the database
class MemberClass(
    override var id: String = "",
    override var username: String = "",
    override var password: String = "",
    override var email: String = "",
    override var medicalType: String = "",
    override var stepHistory: String = ""
) : Member {

    override fun toString(): String {
        return "Member(id='$id', username='$username', password='$password', email='$email', medicalType='$medicalType', stepHistory='$stepHistory')"
    }

    @Exclude
    override fun toMemberMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "username" to username,
            "password" to password,
            "email" to email,
            "medicalType" to medicalType,
            "stepHistory" to stepHistory
        )
    }
}