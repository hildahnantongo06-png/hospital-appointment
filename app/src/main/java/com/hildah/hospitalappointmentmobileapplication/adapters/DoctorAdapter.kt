package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemDoctorBinding
import com.hildah.hospitalappointmentmobileapplication.models.Doctor

class DoctorAdapter(
    private var doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(val binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.binding.tvDoctorName.text = doctor.name
        holder.binding.tvSpecialty.text = doctor.specialty
        holder.binding.tvHospitalExperience.text = "${doctor.hospital} • ${doctor.experience} yrs exp."
        holder.binding.imgDoctor.setImageResource(doctor.imageResId)

        holder.itemView.setOnClickListener {
            onDoctorClick(doctor)
        }
    }

    override fun getItemCount(): Int = doctors.size

    fun updateList(newList: List<Doctor>) {
        doctors = newList
        notifyDataSetChanged()
    }
}
