package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.enums.Speciality;
import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.model.Patient;
import com.hexJune.CodingTest.model.PatientDoctor;
import com.hexJune.CodingTest.repository.DoctorRepository;
import com.hexJune.CodingTest.repository.PatientDoctorRepository;
import com.hexJune.CodingTest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientDoctorService {

    private final PatientDoctorRepository patientDoctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public PatientDoctorService(PatientDoctorRepository patientDoctorRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientDoctorRepository = patientDoctorRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }


    public PatientDoctor makeAppointment(int patientId, int doctorId, LocalDate appointmentDate) {
        // check the patient exists or not
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found"));

        // check the doctor exists or not
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor Not Found"));

        // if existed then set the role to PatientDoctor(Appointment)
        PatientDoctor appointment = new PatientDoctor();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);

        return patientDoctorRepository.save(appointment);

    }

    public List<PatientDoctor> getPatientByDoctorsSpeciality(String speciality) {

        Speciality doctorSpeciality;
        doctorSpeciality = Speciality.valueOf(speciality.toUpperCase());
        return patientDoctorRepository.findByDoctorSpeciality(doctorSpeciality);
    }
}
