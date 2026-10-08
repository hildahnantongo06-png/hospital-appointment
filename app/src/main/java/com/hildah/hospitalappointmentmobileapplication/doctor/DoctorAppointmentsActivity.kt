package com.hildah.hospitalappointmentmobileapplication.doctor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hildah.hospitalappointmentmobileapplication.adapters.AppointmentAdapter
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityDoctorAppointmentsBinding

class DoctorAppointmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorAppointmentsBinding
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var sessionManager: SessionManager
    private var currentTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvDoctorAppointments.layoutManager = LinearLayoutManager(this)
        appointmentAdapter = AppointmentAdapter(
            appointments = emptyList(),
            isDoctorView = true,
            onConfirmClick = { apt ->
                AppointmentRepository.updateAppointmentStatus(apt.id, "Confirmed")
                Toast.makeText(this, "Appointment confirmed", Toast.LENGTH_SHORT).show()
                loadAppointments(currentTab)
            },
            onCancelClick = { apt ->
                AppointmentRepository.updateAppointmentStatus(apt.id, "Cancelled")
                Toast.makeText(this, "Appointment cancelled", Toast.LENGTH_SHORT).show()
                loadAppointments(currentTab)
            },
            onCompleteClick = { apt ->
                AppointmentRepository.updateAppointmentStatus(apt.id, "Completed")
                Toast.makeText(this, "Appointment marked as completed", Toast.LENGTH_SHORT).show()
                loadAppointments(currentTab)
            }
        )
        binding.rvDoctorAppointments.adapter = appointmentAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentTab = tab?.position ?: 0
                loadAppointments(currentTab)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        loadAppointments(0)
    }

    private fun loadAppointments(tabPosition: Int) {
        val doctorName = sessionManager.getUserName()
        val allDoctorAppointments = AppointmentRepository.getAppointmentsForDoctor(doctorName)
            .ifEmpty { AppointmentRepository.getAllAppointments() }

        val filtered = when (tabPosition) {
            0 -> allDoctorAppointments.filter { it.status == "Pending" || it.status == "Confirmed" }
            1 -> allDoctorAppointments.filter { it.status == "Confirmed" }
            else -> allDoctorAppointments.filter { it.status == "Completed" || it.status == "Cancelled" }
        }

        appointmentAdapter.updateList(filtered)
    }
}
