package com.hexJune.CodingTest.repository;

import com.hexJune.CodingTest.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("select d from Doctor d where d.user.username = ?1")
    Doctor getDoctorByUsername(String username);
}
