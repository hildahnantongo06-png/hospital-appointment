package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemAppointmentBinding
import com.hildah.hospitalappointmentmobileapplication.models.Appointment

class AppointmentAdapter(
    private var appointments: List<Appointment>,
    private val isDoctorView: Boolean = false,
    private val onConfirmClick: ((Appointment) -> Unit)? = null,
    private val onCancelClick: ((Appointment) -> Unit)? = null,
    private val onCompleteClick: ((Appointment) -> Unit)? = null
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(val binding: ItemAppointmentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val item = appointments[position]
        val context = holder.itemView.context

        holder.binding.tvDateTime.text = "${item.date} • ${item.time}"
        holder.binding.tvStatus.text = item.status

        when (item.status.lowercase()) {
            "confirmed" -> {
                holder.binding.tvStatus.setBackgroundResource(R.drawable.badge_confirmed)
                holder.binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.success_green))
            }
            "pending" -> {
                holder.binding.tvStatus.setBackgroundResource(R.drawable.badge_pending)
                holder.binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.warning_orange))
            }
            "cancelled" -> {
                holder.binding.tvStatus.setBackgroundResource(R.drawable.badge_cancelled)
                holder.binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.danger_red))
            }
            else -> {
                holder.binding.tvStatus.setBackgroundResource(R.drawable.badge_completed)
                holder.binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.primary_blue))
            }
        }

        if (isDoctorView) {
            holder.binding.tvDoctorOrPatientName.text = "Patient: ${item.patientName}"
            holder.binding.tvSpecialtyOrReason.text = "Reason: ${item.reason}"
            holder.binding.layoutActionButtons.visibility = if (item.status == "Pending" || item.status == "Confirmed") View.VISIBLE else View.GONE
        } else {
            holder.binding.tvDoctorOrPatientName.text = item.doctorName
            holder.binding.tvSpecialtyOrReason.text = "${item.specialty} • ${item.reason}"
            holder.binding.layoutActionButtons.visibility = View.GONE
        }

        holder.binding.btnConfirm.setOnClickListener { onConfirmClick?.invoke(item) }
        holder.binding.btnCancel.setOnClickListener { onCancelClick?.invoke(item) }
        holder.binding.btnComplete.setOnClickListener { onCompleteClick?.invoke(item) }
    }

    override fun getItemCount(): Int = appointments.size

    fun updateList(newList: List<Appointment>) {
        appointments = newList
        notifyDataSetChanged()
    }
}
