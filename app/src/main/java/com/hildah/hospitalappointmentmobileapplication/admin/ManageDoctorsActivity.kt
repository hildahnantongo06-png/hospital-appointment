package com.hildah.hospitalappointmentmobileapplication.admin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.adapters.DoctorAdapter
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityManageDoctorsBinding
import com.hildah.hospitalappointmentmobileapplication.models.Doctor

class ManageDoctorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDoctorsBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private var doctorList = mutableListOf<Doctor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        doctorList = AppointmentRepository.getAllDoctors()

        binding.rvDoctors.layoutManager = LinearLayoutManager(this)
        doctorAdapter = DoctorAdapter(doctorList) { doctor ->
            showDoctorOptionsDialog(doctor)
        }
        binding.rvDoctors.adapter = doctorAdapter

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                val filtered = doctorList.filter {
                    it.name.contains(query, ignoreCase = true) || it.specialty.contains(query, ignoreCase = true)
                }
                doctorAdapter.updateList(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.fabAddDoctor.setOnClickListener {
            showAddDoctorDialog()
        }
    }

    private fun showDoctorOptionsDialog(doctor: Doctor) {
        val options = arrayOf("Edit Doctor", "Delete Doctor", "Cancel")
        AlertDialog.Builder(this)
            .setTitle(doctor.name)
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> showEditDoctorDialog(doctor)
                    1 -> {
                        AppointmentRepository.deleteDoctor(doctor.id)
                        doctorAdapter.updateList(AppointmentRepository.getAllDoctors())
                        Toast.makeText(this, "${doctor.name} deleted", Toast.LENGTH_SHORT).show()
                    }
                    else -> dialog.dismiss()
                }
            }
            .show()
    }

    private fun showAddDoctorDialog() {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 20, 50, 20)
        }

        val etName = EditText(this).apply { hint = "Doctor Name (e.g. Dr. Jane Doe)" }
        val etSpecialty = EditText(this).apply { hint = "Specialty (e.g. Cardiologist)" }
        val etHospital = EditText(this).apply { hint = "Hospital / Room (e.g. Room 10)" }

        layout.addView(etName)
        layout.addView(etSpecialty)
        layout.addView(etHospital)

        AlertDialog.Builder(this)
            .setTitle("Add New Doctor")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val name = etName.text.toString().trim()
                val specialty = etSpecialty.text.toString().trim()
                val hospital = etHospital.text.toString().trim()

                if (name.isNotEmpty() && specialty.isNotEmpty()) {
                    val newDoc = Doctor(
                        id = "doc_" + System.currentTimeMillis(),
                        name = if (name.startsWith("Dr.")) name else "Dr. $name",
                        specialty = specialty,
                        hospital = if (hospital.isEmpty()) "Main Hospital" else hospital,
                        experience = 5,
                        rating = 4.8,
                        imageResId = R.drawable.doctor_1
                    )
                    AppointmentRepository.addDoctor(newDoc)
                    doctorAdapter.updateList(AppointmentRepository.getAllDoctors())
                    Toast.makeText(this, "Doctor added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Name and specialty required", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDoctorDialog(doctor: Doctor) {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 20, 50, 20)
        }

        val etName = EditText(this).apply { setText(doctor.name) }
        val etSpecialty = EditText(this).apply { setText(doctor.specialty) }

        layout.addView(etName)
        layout.addView(etSpecialty)

        AlertDialog.Builder(this)
            .setTitle("Edit Doctor")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                val updated = doctor.copy(
                    name = etName.text.toString().trim(),
                    specialty = etSpecialty.text.toString().trim()
                )
                AppointmentRepository.updateDoctor(updated)
                doctorAdapter.updateList(AppointmentRepository.getAllDoctors())
                Toast.makeText(this, "Doctor updated", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
