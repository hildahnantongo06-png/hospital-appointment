package com.hildah.hospitalappointmentmobileapplication.models

import java.io.Serializable

data class Patient(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val gender: String = "Not specified",
    val age: Int = 0
) : Serializable
