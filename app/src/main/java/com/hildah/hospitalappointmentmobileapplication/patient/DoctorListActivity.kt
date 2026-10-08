package com.hildah.hospitalappointmentmobileapplication.patient

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hildah.hospitalappointmentmobileapplication.adapters.DoctorAdapter
import com.hildah.hospitalappointmentmobileapplication.data.AppointmentRepository
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityDoctorListBinding
import com.hildah.hospitalappointmentmobileapplication.models.Doctor
import com.hildah.hospitalappointmentmobileapplication.utils.Constants

class DoctorListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorListBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private var allDoctors = listOf<Doctor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allDoctors = AppointmentRepository.getAllDoctors()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvDoctors.layoutManager = LinearLayoutManager(this)
        doctorAdapter = DoctorAdapter(allDoctors) { doctor ->
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_DOCTOR, doctor)
            startActivity(intent)
        }
        binding.rvDoctors.adapter = doctorAdapter

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterDoctors(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            val query = binding.etSearch.text.toString()
            filterDoctors(query)
        }
    }

    private fun filterDoctors(query: String) {
        val selectedFilter = when (binding.chipGroupFilter.checkedChipId) {
            binding.chipGeneral.id -> "General"
            binding.chipCardiologist.id -> "Cardiologist"
            binding.chipPediatrician.id -> "Pediatrician"
            else -> "All"
        }

        val filtered = allDoctors.filter { doc ->
            val matchesQuery = query.isEmpty() ||
                    doc.name.contains(query, ignoreCase = true) ||
                    doc.specialty.contains(query, ignoreCase = true)

            val matchesFilter = if (selectedFilter == "All") {
                true
            } else {
                doc.specialty.contains(selectedFilter, ignoreCase = true)
            }

            matchesQuery && matchesFilter
        }

        doctorAdapter.updateList(filtered)
    }
}
