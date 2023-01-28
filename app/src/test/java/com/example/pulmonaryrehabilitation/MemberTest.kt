package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.Model.Member
import org.junit.Assert.*
import org.junit.Test

class MemberTest {

    private val member = Member(
        "1", "user1", "password",
        "user1@example.com"
    )

    @Test
    fun testToString() {
        assertEquals(
            "Member(id='1', username='user1', password='password', " +
                "email='user1@example.com')",
            member.toString()
        )
    }

    @Test
    fun testToMemberMap() {
        assertEquals(
            mapOf(
                "id" to "1",
                "username" to "user1",
                "password" to "password",
                "email" to "user1@example.com"
            ),
            member.toMemberMap()
        )
    }
}