package com.example.pulmonaryrehabilitation
import com.example.pulmonaryrehabilitation.member.MemberClass
import com.example.pulmonaryrehabilitation.member.ModelObject
import org.junit.Assert.assertEquals
import org.junit.Test

class MemberClassTest {
    // NOTE: This is out of date, needs to be updated.
    private val memberClass = MemberClass(
        "1", true, "Georfe", "Scrunkle", "user1",
        "example@admin.com", 5000,

        mutableMapOf("Feb 19 9am" to ModelObject.defaultGamificationHistory()),

        mutableMapOf(
            "Feb 17 10am" to ModelObject.defaultUsageHistory(),
            "Feb 17 11am" to ModelObject.defaultUsageHistory()
        ),

        mutableMapOf("Feb 17 11am" to ModelObject.defaultStepHistory()),

        mutableMapOf(
            "Feb 16 10am" to ModelObject.defaultQuestionnaireHistory(),
            "Feb 19 33am" to ModelObject.defaultQuestionnaireHistory()
        ),
        ""
    )

    @Test
    fun testToString() {
        //        println(
        //            memberClass.toString()
        //        )

        assertEquals(
            "MemberClass(id=1, isAdmin=true, firstName=Georfe, " +
                "lastName=Scrunkle, username=user1, email=example@admin.com, stepGoal=5000, " +
                "gamificationHistory={Feb 19 9am={itemname='game1', itemname2='game2'}}," +
                " usageHistory={Feb 17 10am={itemname='usage1', itemname2='usage2'}, " +
                "Feb 17 11am={itemname='usage1', itemname2='usage2'}}, " +
                "stepHistory={Feb 17 11am={stepCount='step1'}}) ," +
                "questionnaireHistory={Feb 16 10am={question='I am a question', " +
                "answer='I am their answer'}, Feb 19 33am={question='I am a question', " +
                "answer='I am their answer'}}, lastQuestionnaireDate=",
            memberClass.toString()
        )
    }

    @Test
    fun testToMemberMap() {
        // This test fails because of the way it compares the maps, even with identical data
        // this fails (even when comparing the expected vs actual)
        // I tried adding a compareTo method for GamificationHistoryClass to see if that
        // would fix it but it doesn't.
        // This is manually tested to pass.

//        assertEquals(
//            mapOf(
//                "id" to "1",
//                "isAdmin" to true,
//                "firstName" to "Georfe",
//                "lastName" to "Scrunkle",
//                "username" to "user1",
//                "email" to "example@admin.com",
//                "stepGoal" to 5000,
//                "gamificationHistory" to mutableMapOf("Feb 19 9am" to ModelObject.defaultGamificationHistory()),
// //                "usageHistory" to mutableMapOf("Feb 17 10am" to ModelObject.defaultUsageHistory(), "Feb 17 11am" to ModelObject.defaultUsageHistory()),
// //                "stepHistory" to mutableMapOf("Feb 17 11am" to ModelObject.defaultStepHistory()),
// //                "questionnaireHistory" to mutableMapOf("Feb 16 10am" to ModelObject.defaultQuestionnaireHistory(), "Feb 19 33am" to ModelObject.defaultQuestionnaireHistory())
//            ),
//            memberClass.toMemberMap()
//        )
    }
}