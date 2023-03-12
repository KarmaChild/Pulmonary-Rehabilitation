package com.example.pulmonaryrehabilitation
import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
class CurrentUserTest {
    @Test
    fun testingCurrentUser() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        val testUser = MemberClass(
            "1", true, "Georfe", "Scrunkle", "1", "example@admin.com",
            5000,
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
            null
        )
        CurrentUser.setData(testUser)
        assertEquals(CurrentUser.getFirstName(), "Georfe")
        assertEquals(CurrentUser.getLastName(), "Scrunkle")
        assertEquals(CurrentUser.getStepGoal(), 5000)
        assertEquals(CurrentUser.getLastQuestionnaireDate(), null)
    }

    @Test
    fun getMondayTest() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        assertEquals(CurrentUser.getMonday("2023-03-08T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-06T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-12T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-20T07:06:19.954Z"), "2023-03-20")
        assertEquals(CurrentUser.getMonday("2023-07-14T00:53:19.954Z"), "2023-07-10")
        assertEquals(CurrentUser.getMonday("2022-07-31T20:39:59.999Z"), "2022-07-25")
        assertEquals(CurrentUser.getMonday("1991-02-16T01:11:06.666Z"), "1991-02-11")
        assertEquals(CurrentUser.getMonday("2040-06-02T03:57:02.222Z"), "2040-05-28")
    }
    @Test
    fun getConvertDate() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        assertEquals(CurrentUser.convertDate("1678292943790"), "2023-03-08T16:29:03.790Z")
        assertEquals(CurrentUser.convertDate("1679295979954"), "2023-03-20T07:06:19.954Z")
        assertEquals(CurrentUser.convertDate("1689295999954"), "2023-07-14T00:53:19.954Z")
        assertEquals(CurrentUser.convertDate("1659299999999"), "2022-07-31T20:39:59.999Z")
        assertEquals(CurrentUser.convertDate("1666666666666"), "2022-10-25T02:57:46.666Z")
        assertEquals(CurrentUser.convertDate("666666666666"), "1991-02-16T01:11:06.666Z")
        assertEquals(CurrentUser.convertDate("2222222222222"), "2040-06-02T03:57:02.222Z")
    }
    @Test
    fun getNextMondayTest() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        assertEquals(CurrentUser.getNextMonday("2023-03-08T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-06T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-12T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-20T07:06:19.954Z"), "2023-03-27")
        assertEquals(CurrentUser.getNextMonday("2023-07-14T00:53:19.954Z"), "2023-07-17")
        assertEquals(CurrentUser.getNextMonday("2022-07-31T20:39:59.999Z"), "2022-08-01")
        assertEquals(CurrentUser.getNextMonday("1991-02-16T01:11:06.666Z"), "1991-02-18")
        assertEquals(CurrentUser.getNextMonday("2040-06-02T03:57:02.222Z"), "2040-06-04")
    }

    @Test
    fun exerciseDataToStringTest() {
        val test = ExerciseDataClass("id", "video", "title", "medicalType", "description")
        Assert.assertEquals(
            "ExerciseDataClass(id='id', video='video', title='title', medicalType='medicalType', description='description')",
            test.toString()
        )
    }

    @Test
    fun toExerciseDataMapTest() {
        val test = ExerciseDataClass("id", "video", "title", "medicalType", "description")
        val testMap = mutableMapOf<String, Any>("id" to "id", "video" to "video", "title" to "title", "medicalType" to "medicalType", "description" to "description")
        Assert.assertEquals(testMap, test.toExerciseDataMap())
    }

    @Test
    fun questionnaireHistoryToStringTest() {
        val test = QuestionnaireHistoryClass("question", "answer")
        Assert.assertEquals(
            "{question='question', answer='answer'}",
            test.toString()
        )
    }

    @Test
    fun toQuestionnaireHistoryMapTest() {
        val test = QuestionnaireHistoryClass("question", "answer")
        val testMap = mutableMapOf<String, Any>("question" to "question", "answer" to "answer")
        Assert.assertEquals(testMap, test.toQuestionnaireHistoryMap())
    }

    @Test
    fun stepHistoryToStringTest() {
        val test = StepHistoryClass("stepCount")
        Assert.assertEquals(
            "{stepCount='stepCount'}",
            test.toString()
        )
    }

    @Test
    fun usageHistoryToStringTest() {
        val test = UsageHistoryClass("itemname", "itemname2")
        Assert.assertEquals(
            "{itemname='itemname', itemname2='itemname2'}",
            test.toString()
        )
    }

    @Test
    fun gamificationHistoryToStringTest() {
        val test = GamificationHistoryClass("itemname", "itemname2")
        Assert.assertEquals(
            "{itemname='itemname', itemname2='itemname2'}",
            test.toString()
        )
    }

    @Test
    fun toCompareToTestCase1() {
        val test = GamificationHistoryClass("itemname", "itemname2")
        val test2 = GamificationHistoryClass("itemname", "itemname2")
        Assert.assertEquals(0, test.compareTo(test2))
    }

    @Test
    fun toCompareToTestCase2() {
        val test = GamificationHistoryClass("itemname", "itemname2")
        val test2 = GamificationHistoryClass("itemname1", "itemname2")
        Assert.assertEquals(-1, test.compareTo(test2))
    }

    @Test
    fun toCompareToTestCase3() {
        val test = GamificationHistoryClass("itemname", "itemname2")
        val test2 = GamificationHistoryClass("itemname", "itemname1")
        Assert.assertEquals(-1, test.compareTo(test2))
    }

    @Test
    fun toCompareToTestCase4() {
        val test = GamificationHistoryClass("itemname", "itemname2")
        val test2 = GamificationHistoryClass("itemname1", "itemname1")
        Assert.assertEquals(-1, test.compareTo(test2))
    }
}