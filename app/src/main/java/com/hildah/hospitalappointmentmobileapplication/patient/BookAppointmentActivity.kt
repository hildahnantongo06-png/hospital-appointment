package com.hildah.hospitalappointmentmobileapplication.patient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityBookAppointmentBinding
import com.hildah.hospitalappointmentmobileapplication.models.Appointment
import com.hildah.hospitalappointmentmobileapplication.models.Doctor
import com.hildah.hospitalappointmentmobileapplication.utils.Constants

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookAppointmentBinding
    private lateinit var sessionManager: SessionManager
    private var doctor: Doctor? = null
    private var selectedDate: String = "Tue, 13 May 2025"
    private var selectedTime: String = "09:00 AM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        doctor = intent.getSerializableExtra(Constants.EXTRA_DOCTOR) as? Doctor
            ?: AppointmentRepository.getAllDoctors().firstOrNull()
        selectedDate = intent.getStringExtra("selected_date") ?: "Tue, 13 May 2025"
        selectedTime = intent.getStringExtra("selected_time") ?: "09:00 AM"

        doctor?.let { doc ->
            binding.tvDoctorName.text = doc.name
            binding.tvSpecialty.text = doc.specialty
            binding.imgDoctor.setImageResource(doc.imageResId)
        }

        binding.tvSelectedDateTime.text = "$selectedDate at $selectedTime"

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnConfirmBooking.setOnClickListener {
            val reason = binding.etReason.text.toString().trim()
            val notes = binding.etNotes.text.toString().trim()

            if (reason.isEmpty()) {
                Toast.makeText(this, "Please enter a reason for your visit", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val patientName = sessionManager.getUserName()
            val docName = doctor?.name ?: "Dr. Sarah Wanjiku"
            val docSpecialty = doctor?.specialty ?: "General Physician"

            val appointment = Appointment(
                id = "apt_" + System.currentTimeMillis(),
                patientName = patientName,
                doctorName = docName,
                specialty = docSpecialty,
                date = selectedDate,
                time = selectedTime,
                reason = reason,
                notes = notes,
                status = "Confirmed"
            )

            val success = AppointmentRepository.addAppointment(appointment)

            if (success) {
                AlertDialog.Builder(this)
                    .setTitle("Booking Confirmed! 🎉")
                    .setMessage("Your appointment with $docName for $selectedDate at $selectedTime has been successfully booked.")
                    .setPositiveButton("View Appointments") { _, _ ->
                        val intent = Intent(this, MyAppointmentsActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    .setCancelable(false)
                    .show()
            } else {
                Toast.makeText(this, "This time slot is already booked for $docName. Please select another time.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
