package com.hildah.hospitalappointmentmobileapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityWelcomeBinding
import com.hildah.hospitalappointmentmobileapplication.patient.PatientDashboardActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, RoleActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.tvGuest.setOnClickListener {
            val session = SessionManager(this)
            session.setSelectedRole("Patient")
            val intent = Intent(this, PatientDashboardActivity::class.java)
            startActivity(intent)
        }
    }
}
