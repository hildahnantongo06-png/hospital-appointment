package com.hildah.hospitalappointmentmobileapplication.patient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hildah.hospitalappointmentmobileapplication.adapters.AppointmentAdapter
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityMyAppointmentsBinding

class MyAppointmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyAppointmentsBinding
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvAppointments.layoutManager = LinearLayoutManager(this)
        appointmentAdapter = AppointmentAdapter(emptyList(), isDoctorView = false)
        binding.rvAppointments.adapter = appointmentAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                loadAppointments(tab?.position ?: 0)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        loadAppointments(0)
    }

    private fun loadAppointments(tabPosition: Int) {
        val allUserAppointments = AppointmentRepository.getAppointmentsForPatient(sessionManager.getUserName())
            .ifEmpty { AppointmentRepository.getAllAppointments() }

        val filtered = if (tabPosition == 0) {
            allUserAppointments.filter { it.status == "Confirmed" || it.status == "Pending" }
        } else {
            allUserAppointments.filter { it.status == "Completed" || it.status == "Cancelled" }
        }

        appointmentAdapter.updateList(filtered)
    }
}
