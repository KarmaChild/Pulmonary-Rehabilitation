package com.example.pulmonaryrehabilitation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }

        setContentView(R.layout.activity_dashboard)
    }
}