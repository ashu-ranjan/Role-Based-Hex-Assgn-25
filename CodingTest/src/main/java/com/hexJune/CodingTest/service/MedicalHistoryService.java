package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.dto.MedicalHistoryDTO;
import com.hexJune.CodingTest.dto.PatientWithHistoryDTO;
import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import com.hexJune.CodingTest.model.MedicalHistory;
import com.hexJune.CodingTest.model.Patient;
import com.hexJune.CodingTest.repository.MedicalHistoryRepository;
import com.hexJune.CodingTest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    public PatientWithHistoryDTO getHistoryByPatientId(int patientId) {

        // check patient exists with particular id
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found"));

        // list the medical history associated with the patient
        List<MedicalHistory> histories = medicalHistoryRepository.findByPatientId(patientId);

        // if medical history list empty then
        if(histories.isEmpty()) {
            throw new ResourceNotFoundException("No medical history found for patient id: " + patientId);
        }

        // set the found history to MedicalHistoryDTO
        List<MedicalHistoryDTO> medicalHistoryDTOS = histories.stream()
                .map(history -> new MedicalHistoryDTO(
                        history.getIllness(),
                        history.getNumOfYears(),
                        history.getCurrentMedication()
                ))
                .toList();

        // return using PatientWithHistoryDTO for cleaner output
        return new PatientWithHistoryDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getUser().getUsername(),
                medicalHistoryDTOS
        );
    }
}
