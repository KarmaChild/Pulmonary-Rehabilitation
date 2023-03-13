package com.example.pulmonaryrehabilitation

import android.annotation.SuppressLint
import android.util.Log
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.example.pulmonaryrehabilitation.member.MemberClass
import com.example.pulmonaryrehabilitation.member.ModelObject
import io.mockk.*
import org.junit.Assert
import org.junit.Test

class QuestionnaireIntegrationTest {
    @SuppressLint("CheckResult")
    @Test
    fun testingCurrentUser() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        val MockMember = MemberClass(
            "1", true, null, null, null,
            "example@admin.com",
            null,
            null,
            null,
            null,
            mutableMapOf(
                "1647158603" to ModelObject.defaultQuestionnaireHistory(),
            ),
            "1647158603"
        )

        mockkObject(CurrentUser)

        CurrentUser.setData(MockMember)

        Assert.assertEquals(CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3), true)
    }
}