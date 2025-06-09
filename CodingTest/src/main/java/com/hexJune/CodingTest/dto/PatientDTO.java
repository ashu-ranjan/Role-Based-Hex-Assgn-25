package com.hexJune.CodingTest.dto;

import java.util.List;

public class PatientDTO {
    private String name;
    private int age;
    private String username;
    private String password;
    private List<MedicalHistoryDTO> medicalHistories;

    public PatientDTO(String name, int age, String username, String password, List<MedicalHistoryDTO> medicalHistories) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.medicalHistories = medicalHistories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MedicalHistoryDTO> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistoryDTO> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
