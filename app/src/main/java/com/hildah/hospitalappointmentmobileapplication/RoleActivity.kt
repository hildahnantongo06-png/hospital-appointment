package com.hildah.hospitalappointmentmobileapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityRoleBinding

class RoleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoleBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.patientCard.setOnClickListener {
            sessionManager.setSelectedRole("Patient")
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "Patient")
            startActivity(intent)
        }

        binding.doctorCard.setOnClickListener {
            sessionManager.setSelectedRole("Doctor")
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "Doctor")
            startActivity(intent)
        }

        binding.adminCard.setOnClickListener {
            sessionManager.setSelectedRole("Admin")
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "Admin")
            startActivity(intent)
        }
    }
}
