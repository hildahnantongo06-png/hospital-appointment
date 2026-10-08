package com.hildah.hospitalappointmentmobileapplication.auth

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "HospitalAppSession"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_EMAIL = "userEmail"
        private const val KEY_USER_PHONE = "userPhone"
        private const val KEY_USER_ROLE = "userRole"
        private const val KEY_SELECTED_ROLE = "selectedRole"
    }

    fun createLoginSession(id: String, name: String, email: String, phone: String, role: String) {
        val editor = prefs.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_USER_ID, id)
        editor.putString(KEY_USER_NAME, name)
        editor.putString(KEY_USER_EMAIL, email)
        editor.putString(KEY_USER_PHONE, phone)
        editor.putString(KEY_USER_ROLE, role)
        editor.apply()
    }

    fun setSelectedRole(role: String) {
        prefs.edit().putString(KEY_SELECTED_ROLE, role).apply()
    }

    fun getSelectedRole(): String {
        return prefs.getString(KEY_SELECTED_ROLE, "Patient") ?: "Patient"
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getUserRole(): String = prefs.getString(KEY_USER_ROLE, "Patient") ?: "Patient"

    fun getUserName(): String = prefs.getString(KEY_USER_NAME, "User") ?: "User"

    fun getUserEmail(): String = prefs.getString(KEY_USER_EMAIL, "") ?: ""

    fun getUserPhone(): String = prefs.getString(KEY_USER_PHONE, "") ?: ""

    fun logout() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}
