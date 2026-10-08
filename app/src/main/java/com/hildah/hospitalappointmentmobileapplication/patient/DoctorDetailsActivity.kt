package com.hildah.hospitalappointmentmobileapplication.patient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hildah.hospitalappointmentmobileapplication.adapters.DateAdapter
import com.hildah.hospitalappointmentmobileapplication.adapters.DateItem
import com.hildah.hospitalappointmentmobileapplication.adapters.TimeSlotAdapter
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityDoctorDetailsBinding
import com.hildah.hospitalappointmentmobileapplication.models.Doctor
import com.hildah.hospitalappointmentmobileapplication.utils.Constants

class DoctorDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorDetailsBinding
    private var doctor: Doctor? = null
    private var selectedDate: String = "Tue, 13 May 2025"
    private var selectedTime: String = "09:00 AM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doctor = intent.getSerializableExtra(Constants.EXTRA_DOCTOR) as? Doctor
            ?: AppointmentRepository.getAllDoctors().firstOrNull()

        doctor?.let { doc ->
            binding.tvDoctorName.text = doc.name
            binding.tvSpecialty.text = doc.specialty
            binding.tvHospital.text = doc.hospital
            binding.tvRating.text = "${doc.rating} ★"
            binding.tvExperience.text = "${doc.experience} Years"
            binding.imgDoctor.setImageResource(doc.imageResId)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnFavorite.setOnClickListener {
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
        }

        val dateList = listOf(
            DateItem("Mon", "12", "Mon, 12 May 2025"),
            DateItem("Tue", "13", "Tue, 13 May 2025"),
            DateItem("Wed", "14", "Wed, 14 May 2025"),
            DateItem("Thu", "15", "Thu, 15 May 2025"),
            DateItem("Fri", "16", "Fri, 16 May 2025")
        )

        binding.rvDates.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dateAdapter = DateAdapter(dateList) { dateItem ->
            selectedDate = dateItem.fullDate
        }
        binding.rvDates.adapter = dateAdapter

        val times = doctor?.availableTimes ?: listOf("08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "02:00 PM", "03:00 PM")
        val timeAdapter = TimeSlotAdapter(times) { time ->
            selectedTime = time
        }
        binding.rvTimeSlots.adapter = timeAdapter

        binding.btnBookAppointment.setOnClickListener {
            val intent = Intent(this, BookAppointmentActivity::class.java)
            intent.putExtra(Constants.EXTRA_DOCTOR, doctor)
            intent.putExtra("selected_date", selectedDate)
            intent.putExtra("selected_time", selectedTime)
            startActivity(intent)
        }
    }
}
