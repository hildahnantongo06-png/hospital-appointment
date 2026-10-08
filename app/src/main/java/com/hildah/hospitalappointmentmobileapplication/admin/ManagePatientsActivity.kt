package com.hildah.hospitalappointmentmobileapplication.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hildah.hospitalappointmentmobileapplication.adapters.PatientAdapter
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityManagePatientsBinding

class ManagePatientsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManagePatientsBinding
    private lateinit var patientAdapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagePatientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvPatients.layoutManager = LinearLayoutManager(this)
        patientAdapter = PatientAdapter(AppointmentRepository.getAllPatients())
        binding.rvPatients.adapter = patientAdapter
    }
}
