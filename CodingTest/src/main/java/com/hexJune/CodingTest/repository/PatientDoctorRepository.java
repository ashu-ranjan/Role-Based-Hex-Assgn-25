package com.hexJune.CodingTest.repository;

import com.hexJune.CodingTest.enums.Speciality;
import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.model.PatientDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {
    List<PatientDoctor> findByDoctor(Doctor doctor);

    List<PatientDoctor> findByDoctorSpeciality(Speciality doctorSpeciality);
}
