package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemDateSlotBinding

data class DateItem(val dayName: String, val dayNumber: String, val fullDate: String)

class DateAdapter(
    private val dates: List<DateItem>,
    private val onDateSelected: (DateItem) -> Unit
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private var selectedPosition = 0

    class DateViewHolder(val binding: ItemDateSlotBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding = ItemDateSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val dateItem = dates[position]
        val context = holder.itemView.context

        holder.binding.tvDayName.text = dateItem.dayName
        holder.binding.tvDayNumber.text = dateItem.dayNumber

        if (position == selectedPosition) {
            holder.binding.layoutDateSlot.setBackgroundResource(R.drawable.primary_button)
            holder.binding.tvDayName.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.binding.tvDayNumber.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.binding.layoutDateSlot.setBackgroundResource(R.drawable.input_background)
            holder.binding.tvDayName.setTextColor(ContextCompat.getColor(context, R.color.text_gray))
            holder.binding.tvDayNumber.setTextColor(ContextCompat.getColor(context, R.color.text_dark))
        }

        holder.itemView.setOnClickListener {
            val prev = selectedPosition
            selectedPosition = holder.bindingAdapterPosition
            notifyItemChanged(prev)
            notifyItemChanged(selectedPosition)
            onDateSelected(dateItem)
        }
    }

    override fun getItemCount(): Int = dates.size

    fun getSelectedDate(): DateItem? = dates.getOrNull(selectedPosition)
}
