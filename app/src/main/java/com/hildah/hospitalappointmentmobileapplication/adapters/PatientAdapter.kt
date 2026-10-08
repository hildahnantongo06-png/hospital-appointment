package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemPatientBinding
import com.hildah.hospitalappointmentmobileapplication.models.Patient

class PatientAdapter(
    private var patients: List<Patient>
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    class PatientViewHolder(val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.binding.tvPatientName.text = patient.name
        holder.binding.tvPatientDetails.text = "${patient.gender}, ${patient.age} yrs • ${patient.email}"
        holder.binding.tvPatientPhone.text = "Phone: ${patient.phone}"
    }

    override fun getItemCount(): Int = patients.size

    fun updateList(newList: List<Patient>) {
        patients = newList
        notifyDataSetChanged()
    }
}
