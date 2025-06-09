package com.hexJune.CodingTest.controller;

import com.hexJune.CodingTest.dto.PatientDTO;
import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.service.DoctorService;
import com.hexJune.CodingTest.service.MedicalHistoryService;
import com.hexJune.CodingTest.service.PatientDoctorService;
import com.hexJune.CodingTest.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class HospitalController {

    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private MedicalHistoryService medicalHistoryService;
    @Autowired private PatientDoctorService patientDoctorService;

    // 1. POST - Add patient with medical history and user details (PATIENT only)
    /*
     * AIM: To post patient with medical history and user details
     * PATH: /api/patients/add
     * Method: POST
     * Response: Patient
     * */
    @PostMapping("/patients/add")
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(patientService.addPatientWithMedicalHistory(patientDTO));
    }

    // add doctor
    /*
     * AIM: To add doctor along with user details
     * PATH: /api/doctor/add
     * Method: POST
     * Response: Doctor
     * */
    @PostMapping("/doctor/add")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    // 2. Make appointment
    /*
     * AIM: To make appointment using patient id and doctors id
     * PATH: /api/make/appointment?patientId=1&doctorId=2?dateOfAppointment=2025-06-10
     * Method: POST
     * PARAM: @RequestParam
     * Response: PatientDoctor
     * */
    @PostMapping("/make/appointment")
    public ResponseEntity<?> makeAppointment(
            @RequestParam int patientId,
            @RequestParam int doctorId,
            @RequestParam LocalDate appointmentDate) {
        return ResponseEntity.ok(patientDoctorService.makeAppointment(patientId, doctorId, appointmentDate));
    }


    // 3. GET - Get all patients by doctor id (DOCTOR only)
    /*
     * AIM: To get all patient under a doctor
     * PATH: /api/get-all/patients/{doctorId}
     * Method: GET
     * PARAM: @PathVariable
     * Response: List<Patient>
     * */
    @GetMapping("/get-all/patients/{doctorId}")
    public ResponseEntity<?> getAllPatients(@PathVariable int doctorId){
        return ResponseEntity.ok(doctorService.getPatientsByDoctorId(doctorId));
    }

    // 4. Get patient record along with the history
    /*
     * AIM: To get patient record along with their history
     * PATH: /api/get/history/{patientId}
     * Method: GET
     * PARAM: @PathVariable
     * Response: PatientWithHistoryDTO
     * */
    @GetMapping("/get/history/{patientId}")
    public ResponseEntity<?> getMedicalHistoryOfPatient(@PathVariable int patientId) {
        return ResponseEntity.ok(medicalHistoryService.getHistoryByPatientId(patientId));
    }



    @GetMapping("/get/patient/bySpeciality")
    public ResponseEntity<?> getPatientByDoctorsSpeciality(@RequestParam String speciality) {
        return ResponseEntity.ok(patientDoctorService.getPatientByDoctorsSpeciality(speciality));
    }

}
