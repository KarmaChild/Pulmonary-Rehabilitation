package com.example.pulmonaryrehabilitation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.model_since_2_17.CurrentUser

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        val currentUserId = CurrentUser.getUserId()
        Log.d("DashBoardActivity", currentUserId)

        setContentView(R.layout.activity_dashboard)
    }
}