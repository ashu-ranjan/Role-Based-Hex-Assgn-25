package com.hexJune.CodingTest.service;

import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.repository.DoctorRepository;
import com.hexJune.CodingTest.repository.PatientRepository;
import com.hexJune.CodingTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }


    public User signUp(User user) {
        // encrypt the password
        String plainPassword = user.getPassword(); // <-- this gives you plain password
        String encodedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encodedPassword); //<-- Now, User has encoded password
        return userRepository.save(user);
    }

    public Object getUserInfo(String username) {
        User user = userRepository.findByUsername(username);
        switch (user.getRole().toUpperCase()){
            case "PATIENT":
                return patientRepository.getPatientByUsername(username);
            case "DOCTOR":
                return doctorRepository.getDoctorByUsername(username);
            default:
                return null;
        }
    }
}
