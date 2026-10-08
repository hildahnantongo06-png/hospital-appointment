package com.hildah.hospitalappointmentmobileapplication.doctor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityDoctorDashboardBinding
import java.util.Calendar

class DoctorDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorDashboardBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        val doctorName = sessionManager.getUserName()
        val greeting = getTimeBasedGreeting()
        binding.tvDoctorGreeting.text = "$greeting, $doctorName 👋"

        val docAppointments = AppointmentRepository.getAppointmentsForDoctor(doctorName)
            .ifEmpty { AppointmentRepository.getAllAppointments() }

        val totalToday = docAppointments.size
        val completed = docAppointments.count { it.status == "Completed" }
        val pending = docAppointments.count { it.status == "Pending" || it.status == "Confirmed" }

        binding.tvTotalCount.text = totalToday.toString()
        binding.tvCompletedCount.text = completed.toString()
        binding.tvPendingCount.text = pending.toString()

        val nextAppointment = docAppointments.firstOrNull { it.status == "Confirmed" || it.status == "Pending" }
        if (nextAppointment != null) {
            binding.tvNextTime.text = nextAppointment.time
            binding.tvNextPatientName.text = nextAppointment.patientName
            binding.tvNextReason.text = nextAppointment.reason
        } else {
            binding.tvNextTime.text = "No upcoming"
            binding.tvNextPatientName.text = "All caught up!"
            binding.tvNextReason.text = "No pending appointments today"
        }

        binding.btnViewDetails.setOnClickListener {
            startActivity(Intent(this, DoctorAppointmentsActivity::class.java))
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_appointments -> {
                    startActivity(Intent(this, DoctorAppointmentsActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, DoctorProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun getTimeBasedGreeting(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            hour in 0..11 -> "Good Morning"
            hour in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}
