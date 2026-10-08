package com.hildah.hospitalappointmentmobileapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hildah.hospitalappointmentmobileapplication.databinding.ItemDepartmentBinding
import com.hildah.hospitalappointmentmobileapplication.models.Department

class DepartmentAdapter(
    private var departments: List<Department>
) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    class DepartmentViewHolder(val binding: ItemDepartmentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val binding = ItemDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val dept = departments[position]
        holder.binding.tvDepartmentName.text = dept.name
        holder.binding.tvDepartmentDesc.text = dept.description
        holder.binding.tvDoctorCount.text = "${dept.doctorCount} Doctors"
    }

    override fun getItemCount(): Int = departments.size

    fun updateList(newList: List<Department>) {
        departments = newList
        notifyDataSetChanged()
    }
}
