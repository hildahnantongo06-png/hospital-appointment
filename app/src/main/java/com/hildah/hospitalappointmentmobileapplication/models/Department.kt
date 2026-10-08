package com.hildah.hospitalappointmentmobileapplication.models

import java.io.Serializable

data class Department(
    val id: String,
    val name: String,
    val description: String,
    val doctorCount: Int = 0
) : Serializable
