package com.example.pulmonaryrehabilitation.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.member.CurrentUser

class BadgeActivity : AppCompatActivity() {
    lateinit var badgeTextView: TextView
    lateinit var badgeImageView: ImageView
    lateinit var toDashboardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        badgeTextView = findViewById(R.id.badgeTextView)
        badgeImageView = findViewById(R.id.badgeImageView)
        toDashboardButton = findViewById(R.id.toDashboardButton)

        badgeTextView.text = CurrentUser.getStreak() // ... to be continue
//        badgeImageView // ... set image according to streak, streak 1, streak 2, streak 4, streak 8 has different badge
        // if (CurrentUser.getStreak() == "1" --> draw appropriate image
        badgeImageView.background = R.drawable.fire.toDrawable()
        toDashboardButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}