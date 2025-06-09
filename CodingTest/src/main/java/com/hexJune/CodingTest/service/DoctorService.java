package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.dto.GetPatientDTO;
import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.model.Patient;
import com.hexJune.CodingTest.model.PatientDoctor;
import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.repository.DoctorRepository;
import com.hexJune.CodingTest.repository.PatientDoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientDoctorRepository patientDoctorRepository;
    private final UserService userService;

    public DoctorService(DoctorRepository doctorRepository, PatientDoctorRepository patientDoctorRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.patientDoctorRepository = patientDoctorRepository;
        this.userService = userService;
    }

    public List<GetPatientDTO> getPatientsByDoctorId(int doctorId) {
        // check the doctor exists or not
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // list the patient having appointment of found doctor
        List<PatientDoctor> patients = patientDoctorRepository.findByDoctor(doctor);

        // set it to the GetPatientDTO for cleaner output
        return patients.stream()
                .map(pd -> {
                    Patient patient = pd.getPatient();
                    return new GetPatientDTO(
                            patient.getName(),
                            patient.getAge(),
                            patient.getUser().getUsername()
                    );
                })
                .toList();
    }

    public Doctor addDoctor(Doctor doctor) {

        // get the user from the doctor
        User user = doctor.getUser();

        // set the as doctor
        user.setRole("DOCTOR");

        // register the user
        user = userService.signUp(user);

        // set the user back to doctor
        doctor.setUser(user);

        // save the doctor
        return doctorRepository.save(doctor);
    }
}
