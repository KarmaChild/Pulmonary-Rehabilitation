package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.member.UsageHistoryClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class UsageHistoryClassTest {

    @Test
    fun usageHistoryToStringTest() {
        val test = UsageHistoryClass("itemname", "itemname2")
        Assert.assertEquals(
            "{itemname='itemname', itemname2='itemname2'}",
            test.toString()
        )
    }
}