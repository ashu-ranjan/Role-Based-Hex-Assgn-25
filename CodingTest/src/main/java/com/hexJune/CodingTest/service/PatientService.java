package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.dto.MedicalHistoryDTO;
import com.hexJune.CodingTest.dto.PatientDTO;
import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import com.hexJune.CodingTest.model.MedicalHistory;
import com.hexJune.CodingTest.model.Patient;
import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.repository.DoctorRepository;
import com.hexJune.CodingTest.repository.MedicalHistoryRepository;
import com.hexJune.CodingTest.repository.PatientRepository;
import com.hexJune.CodingTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.ui.OneTimeTokenSubmitPageGeneratingWebFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final UserService userService;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          MedicalHistoryRepository medicalHistoryRepository,
                          UserService userService) {
        this.patientRepository = patientRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.userService = userService;
    }

    public PatientDTO addPatientWithMedicalHistory(PatientDTO patientDTO) {

        // set the user credentials by getting it form PatientDTO
        User user = new User();
        user.setUsername(patientDTO.getUsername());
        user.setPassword(patientDTO.getPassword());
        user.setRole("PATIENT");

        // register the user
        user = userService.signUp(user);

        // set the patient info from the PatientDTO
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());
        patient.setUser(user); // set the user to the patient

        // save the patient
        patientRepository.save(patient);

        // initialize the list for saving the history of the patient and setting all  the medicalHistory
        List<MedicalHistory> medicalHistories = patientDTO.getMedicalHistories()
                .stream().map(mhDto -> {
                    MedicalHistory mh = new MedicalHistory();
                    mh.setIllness(mhDto.getIllness());
                    mh.setNumOfYears(mhDto.getNumberOfYears());
                    mh.setCurrentMedication(mhDto.getCurrentMedication());
                    mh.setPatient(patient);
                    return mh;
                }).toList();

        // saving all the medical history using saveAll()
        medicalHistoryRepository.saveAll(medicalHistories);
        return patientDTO;
    }

}
