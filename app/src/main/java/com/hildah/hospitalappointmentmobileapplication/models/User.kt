package com.hildah.hospitalappointmentmobileapplication.models

import java.io.Serializable

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val role: String
) : Serializable
