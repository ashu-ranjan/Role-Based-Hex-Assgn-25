package com.hexJune.CodingTest.repository;

import com.hexJune.CodingTest.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
    List<MedicalHistory> findByPatientId(int patientId);
}
