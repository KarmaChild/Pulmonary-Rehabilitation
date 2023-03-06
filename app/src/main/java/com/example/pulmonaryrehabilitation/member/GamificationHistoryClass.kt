package com.example.pulmonaryrehabilitation.member

class GamificationHistoryClass(
    override var itemname: String,
    override var itemname2: String
) : GamificationHistory, Comparable<GamificationHistoryClass> {
    override fun compareTo(other: GamificationHistoryClass): Int {
        println("Hello")
        val other1 = other.itemname
        val other2 = other.itemname2
        if (itemname != other1) {
            return -1
        }
        if (itemname2 != other2) {
            return -1
        }
        return 0
    }

    override fun toString(): String {
        return "{itemname='$itemname', itemname2='$itemname2'}"
    }
}

/*
Rectangle(val length: Int, val breadth: Int): Comparable<Rectangle>{
    override fun compareTo(other: Rectangle): Int {
        val area1 = length * breadth
        val area2 = other.length * other.breadth

        // Comparing two rectangles on the basis of area
        if(area1 == area2){
            return 0;
        }else if(area1 < area2){
            return -1;
        }
        return 1;
    }
 */