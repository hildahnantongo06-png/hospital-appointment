package com.hildah.hospitalappointmentmobileapplication.patient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.admin.ManageDepartmentsActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityPatientDashboardBinding

class PatientDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientDashboardBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        val userName = sessionManager.getUserName()
        binding.tvGreeting.text = "Hello, $userName 👋"

        binding.layoutSearch.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        binding.cardBookAppointment.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        binding.cardMyAppointments.setOnClickListener {
            startActivity(Intent(this, MyAppointmentsActivity::class.java))
        }

        binding.cardDoctors.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        binding.cardDepartments.setOnClickListener {
            startActivity(Intent(this, ManageDepartmentsActivity::class.java))
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_appointments -> {
                    startActivity(Intent(this, MyAppointmentsActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, PatientProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
