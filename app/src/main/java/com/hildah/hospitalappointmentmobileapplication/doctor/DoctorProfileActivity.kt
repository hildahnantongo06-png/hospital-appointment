package com.hildah.hospitalappointmentmobileapplication.doctor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.WelcomeActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityPatientProfileBinding

class DoctorProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientProfileBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.tvProfileName.text = sessionManager.getUserName()
        binding.tvProfileEmail.text = sessionManager.getUserEmail().ifEmpty { "doctor@gmail.com" }
        binding.tvProfileRole.text = "Role: Doctor"

        binding.btnLogout.setOnClickListener {
            sessionManager.logout()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
