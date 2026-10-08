package com.hildah.hospitalappointmentmobileapplication.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityRegisterBinding
import com.hildah.hospitalappointmentmobileapplication.utils.NavigationUtils
import com.hildah.hospitalappointmentmobileapplication.utils.ValidationUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        val initialRole = intent.getStringExtra("ROLE") ?: "Patient"
        when (initialRole.lowercase()) {
            "doctor" -> binding.rbDoctor.isChecked = true
            "admin" -> binding.rbAdmin.isChecked = true
            else -> binding.rbPatient.isChecked = true
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            val selectedRole = when (binding.rgRole.checkedRadioButtonId) {
                R.id.rbDoctor -> "Doctor"
                R.id.rbAdmin -> "Admin"
                else -> "Patient"
            }

            if (!ValidationUtils.isValidName(name)) {
                Toast.makeText(this, "Please enter a valid full name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!ValidationUtils.isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!ValidationUtils.isValidPhone(phone)) {
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!ValidationUtils.isValidPassword(password)) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sessionManager.createLoginSession(
                id = "usr_" + System.currentTimeMillis(),
                name = name,
                email = email,
                phone = phone,
                role = selectedRole
            )

            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
            NavigationUtils.navigateToDashboardForRole(this, selectedRole)
        }
    }
}
