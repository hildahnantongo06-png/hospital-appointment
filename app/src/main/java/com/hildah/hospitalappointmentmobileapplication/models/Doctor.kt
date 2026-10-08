package com.hildah.hospitalappointmentmobileapplication.models

import java.io.Serializable

data class Doctor(
    val id: String,
    val name: String,
    val specialty: String,
    val hospital: String,
    val experience: Int,
    val rating: Double,
    val imageResId: Int,
    val isActive: Boolean = true,
    val availableTimes: List<String> = listOf("08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "02:00 PM", "03:00 PM")
) : Serializable
