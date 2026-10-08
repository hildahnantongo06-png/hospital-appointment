package com.hildah.hospitalappointmentmobileapplication.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.data.MockData
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityAdminDashboardBinding
import com.hildah.hospitalappointmentmobileapplication.doctor.DoctorProfileActivity

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalDoctors = AppointmentRepository.getAllDoctors().size
        val totalPatients = AppointmentRepository.getAllPatients().size
        val totalAppointments = AppointmentRepository.getAllAppointments().size
        val totalDepartments = MockData.getDepartments().size

        binding.tvTotalDoctors.text = totalDoctors.toString()
        binding.tvTotalPatients.text = totalPatients.toString()
        binding.tvTotalAppointments.text = totalAppointments.toString()
        binding.tvTotalDepartments.text = totalDepartments.toString()

        binding.cardManageDoctors.setOnClickListener {
            startActivity(Intent(this, ManageDoctorsActivity::class.java))
        }

        binding.cardManagePatients.setOnClickListener {
            startActivity(Intent(this, ManagePatientsActivity::class.java))
        }

        binding.cardManageDepartments.setOnClickListener {
            startActivity(Intent(this, ManageDepartmentsActivity::class.java))
        }

        binding.cardAdminReports.setOnClickListener {
            startActivity(Intent(this, AdminReportsActivity::class.java))
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_manage -> {
                    startActivity(Intent(this, ManageDoctorsActivity::class.java))
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
}
