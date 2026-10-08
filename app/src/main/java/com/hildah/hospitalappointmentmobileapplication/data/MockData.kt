package com.hildah.hospitalappointmentmobileapplication.data

import com.hildah.hospitalappointmentmobileapplication.R
import com.hildah.hospitalappointmentmobileapplication.models.Appointment
import com.hildah.hospitalappointmentmobileapplication.models.Department
import com.hildah.hospitalappointmentmobileapplication.models.Doctor
import com.hildah.hospitalappointmentmobileapplication.models.Patient

object MockData {

    fun getDoctors(): MutableList<Doctor> {
        return mutableListOf(
            Doctor(
                id = "doc1",
                name = "Dr. Sarah Wanjiku",
                specialty = "General Physician",
                hospital = "Main Hospital, Room 12",
                experience = 5,
                rating = 4.8,
                imageResId = R.drawable.doctor_1
            ),
            Doctor(
                id = "doc2",
                name = "Dr. John Mwangi",
                specialty = "Cardiologist",
                hospital = "Cardiology Wing, Room 04",
                experience = 8,
                rating = 4.9,
                imageResId = R.drawable.doctor_2
            ),
            Doctor(
                id = "doc3",
                name = "Dr. Amina Hassan",
                specialty = "Pediatrician",
                hospital = "Children's Ward, Room 08",
                experience = 6,
                rating = 4.7,
                imageResId = R.drawable.doctor_3
            ),
            Doctor(
                id = "doc4",
                name = "Dr. James Otieno",
                specialty = "Orthopedic Surgeon",
                hospital = "Surgical Block, Room 15",
                experience = 10,
                rating = 4.9,
                imageResId = R.drawable.doctor_4
            ),
            Doctor(
                id = "doc5",
                name = "Dr. Grace Muthoni",
                specialty = "Gynecologist",
                hospital = "Maternity Wing, Room 03",
                experience = 7,
                rating = 4.8,
                imageResId = R.drawable.doctor_5
            ),
            Doctor(
                id = "doc6",
                name = "Dr. David Ochieng",
                specialty = "Emergency Specialist",
                hospital = "Emergency Dept, Room 01",
                experience = 9,
                rating = 4.6,
                imageResId = R.drawable.doctor_6
            )
        )
    }

    fun getDepartments(): List<Department> {
        return listOf(
            Department("dep1", "General Medicine", "Primary healthcare & routine checkups", 12),
            Department("dep2", "Cardiology", "Heart health & cardiovascular care", 8),
            Department("dep3", "Pediatrics", "Infant, child & adolescent health", 10),
            Department("dep4", "Orthopedics", "Bones, joints, & musculoskeletal care", 6),
            Department("dep5", "Gynecology", "Women's reproductive health", 7),
            Department("dep6", "Emergency", "Urgent & 24/7 emergency services", 15)
        )
    }

    fun getInitialAppointments(): MutableList<Appointment> {
        return mutableListOf(
            Appointment(
                id = "apt1",
                patientName = "Hildah Wanjiku",
                doctorName = "Dr. Sarah Wanjiku",
                specialty = "General Physician",
                date = "Tue, 13 May 2025",
                time = "09:00 AM",
                reason = "General Checkup",
                notes = "Routine health screening",
                status = "Confirmed"
            ),
            Appointment(
                id = "apt2",
                patientName = "Peter Kimani",
                doctorName = "Dr. James Otieno",
                specialty = "Orthopedic Surgeon",
                date = "Fri, 16 May 2025",
                time = "11:30 AM",
                reason = "Knee pain consultation",
                notes = "Follow up from last week",
                status = "Pending"
            ),
            Appointment(
                id = "apt3",
                patientName = "Aisha Njeri",
                doctorName = "Dr. Amina Hassan",
                specialty = "Pediatrician",
                date = "Mon, 19 May 2025",
                time = "02:00 PM",
                reason = "Pediatric Consultation",
                notes = "Child vaccination check",
                status = "Confirmed"
            ),
            Appointment(
                id = "apt4",
                patientName = "Samuel Omondi",
                doctorName = "Dr. John Mwangi",
                specialty = "Cardiologist",
                date = "Wed, 07 May 2025",
                time = "10:00 AM",
                reason = "ECG & Heart Checkup",
                notes = "Completed ECG assessment",
                status = "Completed"
            )
        )
    }

    fun getPatients(): MutableList<Patient> {
        return mutableListOf(
            Patient("pat1", "Hildah Wanjiku", "hildah@gmail.com", "0712345678", "Female", 24),
            Patient("pat2", "Peter Kimani", "peter@gmail.com", "0723456789", "Male", 32),
            Patient("pat3", "Aisha Njeri", "aisha@gmail.com", "0734567890", "Female", 28),
            Patient("pat4", "Samuel Omondi", "samuel@gmail.com", "0745678901", "Male", 45)
        )
    }
}
