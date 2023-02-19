package com.example.pulmonaryrehabilitation
import com.example.pulmonaryrehabilitation.model_since_2_17.CurrentUser
import com.example.pulmonaryrehabilitation.model_since_2_17.MemberClass
import com.example.pulmonaryrehabilitation.model_since_2_17.ModelObject
import org.junit.Assert.*
import org.junit.Test
class CurrestUserTest {
    @Test
    fun testingCurrentUser() {
        val testUser = MemberClass(
            "1", true, "Georfe", "Scrunkle", "1", "example@admin.com", 5000, mapOf("Feb 19 9am" to ModelObject.defaultGamificationHistory()),
            mapOf("Feb 17 10am" to ModelObject.defaultUsageHistory(), "Feb 17 11am" to ModelObject.defaultUsageHistory()),
            mapOf("Feb 17 11am" to ModelObject.defaultStepHistory()), mapOf("Feb 16 10am" to ModelObject.defaultQuestionnaireHistory(), "Feb 19 33am" to ModelObject.defaultQuestionnaireHistory())
        )
        CurrentUser.setData(testUser)
        assertEquals(CurrentUser.getFirstName(), "Georfe")
        assertEquals(CurrentUser.getLastName(), "Scrunkle")
        assertEquals(CurrentUser.getStepGoal(), 5000)
    }
}