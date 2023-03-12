package com.example.pulmonaryrehabilitation

import android.util.Log
import com.example.pulmonaryrehabilitation.member.CurrentUser
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.*
import org.junit.Test

class CurrentUserIntegrationTest {

    /**
     * Integration Testing with getNextMonday() and getConvertDate()
     * This test should get the next Monday by converting Epoch Milliseconds of the current date to
     * find the next Monday.
     */
    @Test
    fun getNextMondayAndGetConvertDateTest() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("0000000000000")), "1970-01-05")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("2222222222222")), "2040-06-04")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1607321856342")), "2020-12-14")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1548643137465")), "2019-02-04")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1489974458123")), "2017-03-27")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1473784512478")), "2016-09-19")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1595350195211")), "2020-07-27")
        assertEquals(CurrentUser.getNextMonday(CurrentUser.convertDate("1517385829734")), "2018-02-05")
    }

    /**
     * Integration Testing with getMonday() and getConvertDate()
     * This test should get the current week Monday by converting Epoch Milliseconds of the current
     * date to find the current week Monday.
     * i.e., current date is Sunday,
     */
    @Test
    fun getMondayAndGetConvertDateTest() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("0000000000000")), "1969-12-29")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("2222222222222")), "2040-05-28")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1607321856342")), "2020-12-07")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1548643137465")), "2019-01-28")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1489974458123")), "2017-03-20")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1473784512478")), "2016-09-12")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1595350195211")), "2020-07-20")
        assertEquals(CurrentUser.getMonday(CurrentUser.convertDate("1517385829734")), "2018-01-29")
    }
}