package com.hildah.hospitalappointmentmobileapplication.utils

import android.util.Patterns

object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.isNotEmpty() && phone.length >= 8
    }

    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.trim().length >= 2
    }
}
