package com.example.pulmonaryrehabilitation.activity.streaks

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import java.util.*
import kotlin.collections.ArrayList

class StreakActivity : AppCompatActivity() {

    // variables for grid view and badge list
    lateinit var badgeGridView: GridView
    lateinit var badgeList: List<StreakBadgeData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streaks_page)

        // initializing variables of grid view with their ids.
        badgeGridView = findViewById(R.id.idGridView)
        badgeList = ArrayList<StreakBadgeData>()

        // on below line we are adding data to
        // our badge list with image and badge name, as well as description and unlock Id.

        badgeList = badgeList + StreakBadgeData("Mile1", "123", R.drawable.badgeicon, 1)
//        badgeList = badgeList + StreakBadgeData("Java", R.drawable.java)
//        badgeList = badgeList + StreakBadgeData("Android", R.drawable.android)
//        badgeList = badgeList + StreakBadgeData("Python", R.drawable.python)
//        badgeList = badgeList + StreakBadgeData("Javascript", R.drawable.js)

        // on below line I am initializing our badge adapter
        // and passing course list and context.
        val badgeAdapter = StreakBadgeView(badgeList = badgeList, this@StreakActivity)

        // on below line we are setting adapter to our grid view.
        badgeGridView.adapter = badgeAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        badgeGridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, badgeList[position].badgeName + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}