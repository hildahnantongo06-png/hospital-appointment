package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemTimeSlotBinding

class TimeSlotAdapter(
    private val timeSlots: List<String>,
    private val onTimeSelected: (String) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    private var selectedPosition = 0

    class TimeSlotViewHolder(val binding: ItemTimeSlotBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val binding = ItemTimeSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeSlotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val time = timeSlots[position]
        val context = holder.itemView.context

        holder.binding.tvTimeSlot.text = time

        if (position == selectedPosition) {
            holder.binding.tvTimeSlot.setBackgroundResource(R.drawable.primary_button)
            holder.binding.tvTimeSlot.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.binding.tvTimeSlot.setBackgroundResource(R.drawable.input_background)
            holder.binding.tvTimeSlot.setTextColor(ContextCompat.getColor(context, R.color.text_dark))
        }

        holder.itemView.setOnClickListener {
            val prev = selectedPosition
            selectedPosition = holder.bindingAdapterPosition
            notifyItemChanged(prev)
            notifyItemChanged(selectedPosition)
            onTimeSelected(time)
        }
    }

    override fun getItemCount(): Int = timeSlots.size

    fun getSelectedTime(): String = timeSlots.getOrNull(selectedPosition) ?: timeSlots.firstOrNull() ?: ""
}
