package com.hildah.hospitalappointmentmobileapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.utils.NavigationUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            val sessionManager = SessionManager(this)
            if (sessionManager.isLoggedIn()) {
                NavigationUtils.navigateToDashboardForRole(this, sessionManager.getUserRole())
            } else {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}
