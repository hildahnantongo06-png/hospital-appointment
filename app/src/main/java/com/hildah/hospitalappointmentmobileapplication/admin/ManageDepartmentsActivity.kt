package com.hildah.hospitalappointmentmobileapplication.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hildah.hospitalappointmentmobileapplication.adapters.DepartmentAdapter
import com.hildah.hospitalappointmentmobileapplication.data.MockData
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityManageDepartmentsBinding

class ManageDepartmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageDepartmentsBinding
    private lateinit var departmentAdapter: DepartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageDepartmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvDepartments.layoutManager = LinearLayoutManager(this)
        departmentAdapter = DepartmentAdapter(MockData.getDepartments())
        binding.rvDepartments.adapter = departmentAdapter
    }
}
