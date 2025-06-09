package com.hexJune.CodingTest;

import com.hexJune.CodingTest.dto.GetPatientDTO;
import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import com.hexJune.CodingTest.model.Doctor;
import com.hexJune.CodingTest.model.Patient;
import com.hexJune.CodingTest.model.PatientDoctor;
import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.repository.DoctorRepository;
import com.hexJune.CodingTest.repository.PatientDoctorRepository;
import com.hexJune.CodingTest.service.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DoctorPatientServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientDoctorRepository patientDoctorRepository;

    private Doctor doctor;
    private Patient patient;
    private User user;
    private PatientDoctor patientDoctor;

    @BeforeEach
    public void init() {
        user = new User();
        user.setUsername("john@gmail.com");
        user.setPassword("john123");

        patient = new Patient();
        patient.setId(1);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setUser(user);

        doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("Dr. Smith");

        patientDoctor = new PatientDoctor();
        patientDoctor.setId(1);
        patientDoctor.setDoctor(doctor);
        patientDoctor.setPatient(patient);
    }

    @Test
    public void getPatientsByDoctorIdTestMock() {
        List<PatientDoctor> patientDoctorList = List.of(patientDoctor);
        List<GetPatientDTO> expectedList = List.of(new GetPatientDTO("John Doe", 30, "john@gmail.com"));

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(patientDoctorRepository.findByDoctor(doctor)).thenReturn(patientDoctorList);

        List<GetPatientDTO> actualList = doctorService.getPatientsByDoctorId(1);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void getPatientsByDoctorIdTest_InvalidDoctorId() {
        when(doctorRepository.findById(999)).thenReturn(Optional.empty());

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
                () -> doctorService.getPatientsByDoctorId(999));

        assertEquals("Doctor not found".toLowerCase(), e.getMessage().toLowerCase());
    }

    @AfterEach
    public void afterTest() {
        doctor = null;
        patient = null;
        user = null;
        patientDoctor = null;
    }
}
