package com.hildah.hospitalappointmentmobileapplication.utils

import android.content.Context
import android.content.Intent
import com.hildah.hospitalappointmentmobileapplication.admin.AdminDashboardActivity
import com.hildah.hospitalappointmentmobileapplication.doctor.DoctorDashboardActivity
import com.hildah.hospitalappointmentmobileapplication.patient.PatientDashboardActivity

object NavigationUtils {

    fun navigateToDashboardForRole(context: Context, role: String) {
        val intent = when (role.lowercase()) {
            "doctor" -> Intent(context, DoctorDashboardActivity::class.java)
            "admin" -> Intent(context, AdminDashboardActivity::class.java)
            else -> Intent(context, PatientDashboardActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}
