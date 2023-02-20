package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.getValue

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        // transitioning from splashActivity to EmailLoginActivity with 2000ms delayed
        Handler().postDelayed({
            startActivity(Intent(this, ExercisePlayerTapViewActivity :: class.java))
        }, 2000)
    }
}