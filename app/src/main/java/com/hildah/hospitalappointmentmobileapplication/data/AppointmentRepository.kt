package com.hildah.hospitalappointmentmobileapplication.data

import com.hildah.hospitalappointmentmobileapplication.models.Appointment
import com.hildah.hospitalappointmentmobileapplication.models.Doctor
import com.hildah.hospitalappointmentmobileapplication.models.Patient
import java.util.UUID

object AppointmentRepository {

    private val appointments = MockData.getInitialAppointments()
    private val doctors = MockData.getDoctors()
    private val patients = MockData.getPatients()

    fun getAllAppointments(): List<Appointment> = appointments

    fun getAppointmentsForPatient(patientName: String): List<Appointment> {
        return appointments.filter { it.patientName.equals(patientName, ignoreCase = true) }
    }

    fun getAppointmentsForDoctor(doctorName: String): List<Appointment> {
        return appointments.filter { it.doctorName.contains(doctorName, ignoreCase = true) || doctorName.contains(it.doctorName, ignoreCase = true) }
    }

    fun isSlotAvailable(doctorName: String, date: String, time: String): Boolean {
        return appointments.none { 
            it.doctorName.equals(doctorName, ignoreCase = true) && 
            it.date == date && 
            it.time == time && 
            it.status != "Cancelled" 
        }
    }

    fun addAppointment(appointment: Appointment): Boolean {
        if (!isSlotAvailable(appointment.doctorName, appointment.date, appointment.time)) {
            return false
        }
        appointments.add(0, appointment)
        return true
    }

    fun updateAppointmentStatus(appointmentId: String, newStatus: String): Boolean {
        val appointment = appointments.find { it.id == appointmentId }
        return if (appointment != null) {
            appointment.status = newStatus
            true
        } else {
            false
        }
    }

    fun getAllDoctors(): MutableList<Doctor> = doctors

    fun getDoctorById(id: String): Doctor? = doctors.find { it.id == id }

    fun addDoctor(doctor: Doctor) {
        doctors.add(doctor)
    }

    fun updateDoctor(doctor: Doctor) {
        val index = doctors.indexOfFirst { it.id == doctor.id }
        if (index != -1) {
            doctors[index] = doctor
        }
    }

    fun deleteDoctor(id: String) {
        doctors.removeAll { it.id == id }
    }

    fun getAllPatients(): MutableList<Patient> = patients

    fun addPatient(patient: Patient) {
        patients.add(patient)
    }
}
