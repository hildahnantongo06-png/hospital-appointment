package com.hildah.hospitalappointmentmobileapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hildah.hospitalappointmentmobileapplication.auth.RegisterActivity
import com.hildah.hospitalappointmentmobileapplication.auth.SessionManager
import com.hildah.hospitalappointmentmobileapplication.databinding.ActivityLoginBinding
import com.hildah.hospitalappointmentmobileapplication.utils.NavigationUtils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        val selectedRole = intent.getStringExtra("ROLE") ?: sessionManager.getSelectedRole()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var role = selectedRole
            var name = "User"

            if (email.contains("doctor", ignoreCase = true) || selectedRole.equals("Doctor", ignoreCase = true)) {
                role = "Doctor"
                name = "Dr. Sarah Wanjiku"
            } else if (email.contains("admin", ignoreCase = true) || selectedRole.equals("Admin", ignoreCase = true)) {
                role = "Admin"
                name = "Administrator"
            } else {
                role = "Patient"
                name = "Hildah Wanjiku"
            }

            sessionManager.createLoginSession(
                id = "usr_" + System.currentTimeMillis(),
                name = name,
                email = email,
                phone = "0712345678",
                role = role
            )

            Toast.makeText(this, "Login successful as $role", Toast.LENGTH_SHORT).show()
            NavigationUtils.navigateToDashboardForRole(this, role)
        }

        binding.tvRegister.setOnClickListener {
            val regIntent = Intent(this, RegisterActivity::class.java)
            regIntent.putExtra("ROLE", selectedRole)
            startActivity(regIntent)
        }

        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Sign-In clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnFacebook.setOnClickListener {
            Toast.makeText(this, "Facebook Sign-In clicked", Toast.LENGTH_SHORT).show()
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Password reset instructions sent to your email", Toast.LENGTH_LONG).show()
        }
    }
}
