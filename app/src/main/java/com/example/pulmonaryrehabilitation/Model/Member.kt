package com.example.pulmonaryrehabilitation.Model

import com.google.firebase.database.Exclude

class Member(
    var id: String? = "",
    var username: String? = "",
    var password: String? = "",
    var email: String? = "",
    var phone: String? = ""
) {
    override fun toString(): String {
        return "Member(id='$id', username='$username', password='$password', email='$email', phone='$phone')"
    }

    @Exclude
    fun toMemberMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "username" to username,
            "password" to password,
            "email" to email,
            "phone" to phone
        )
    }
}