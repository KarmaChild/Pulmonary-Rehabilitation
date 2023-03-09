package com.example.pulmonaryrehabilitation.activity.streaks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pulmonaryrehabilitation.R

// creating an adapter class for the gridview
internal class StreakBadgeView(
    // variable list to store all our badges and context
    private val badgeList: List<StreakBadgeData>,
    private val context: Context

) :
    BaseAdapter() {
    // variable to store layoutInflater
    // variables to store text view and image view
    private var layoutInflater: LayoutInflater? = null
    private lateinit var badgeTextView: TextView
    private lateinit var badgeImageView: ImageView

    // return the amount of items in the badge list
    override fun getCount(): Int {

        return badgeList.size
    }

    // below stub function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below stub function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        badgeImageView = convertView!!.findViewById(R.id.idImageViewBadge)
        badgeTextView = convertView!!.findViewById(R.id.idTextViewBadge)
        // on below line we are setting image for our course image view.
        badgeImageView.setImageResource(badgeList.get(position).badgeIcon)
        // on below line we are setting text in our course text view.
        badgeTextView.setText(badgeList.get(position).badgeName)
        // at last we are returning our convert view.
        return convertView
    }
}