package com.example.pulmonaryrehabilitation

import com.example.pulmonaryrehabilitation.member.GamificationHistoryClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class GamificationHistoryClassTest {

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