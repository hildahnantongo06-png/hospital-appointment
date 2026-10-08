package com.hildah.hospitalappointmentmobileapplication.models

import java.io.Serializable

data class Appointment(
    val id: String,
    val patientName: String,
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String,
    val reason: String,
    val notes: String = "",
    var status: String = "Confirmed"
) : Serializable
