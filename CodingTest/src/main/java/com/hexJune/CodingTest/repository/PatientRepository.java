package com.hexJune.CodingTest.repository;

import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("select p from Patient p where p.user.username = ?1")
    Patient getPatientByUsername(String username);

}
